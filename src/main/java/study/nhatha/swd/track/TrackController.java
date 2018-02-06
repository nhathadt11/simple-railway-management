package study.nhatha.swd.track;

import study.nhatha.swd.console.Inputer;
import study.nhatha.swd.console.Printer;
import study.nhatha.swd.generic.AppController;
import study.nhatha.swd.menu.Menu;
import study.nhatha.swd.menu.SimpleMenu;
import study.nhatha.swd.station.StationController;
import study.nhatha.swd.console.Notification;

import java.util.LinkedHashMap;
import java.util.Map;

public class TrackController implements AppController {
  private TrackDao trackDao;
  private StationController stationController;
  private Menu menu;

  public TrackController() {
    this.trackDao = new TrackDao();
    this.stationController = new StationController();
    this.initMenu();
  }

  private void initMenu() {
    Map<String, Menu.Action> actions = new LinkedHashMap<>();

    actions.put("1. Add"      , this::add);
    actions.put("2. Add Many" , this::addMany);
    actions.put("3. Update"   , this::update);
    actions.put("4. Delete"   , this::delete);
    actions.put("5. Find"     , this::find);
    actions.put("6. List"     , this::all);

    menu = new SimpleMenu("TRACK", actions);
  }

  @Override
  public void add() {
    listStations();
    trackDao.add(requestTrack());
  }

  @Override
  public void addMany() {
    Notification.error("Unsupported feature.");
  }

  @Override
  public void update() {
    String code = Inputer.requestString("Find Code? ");

    Track found = trackDao.queryByCode(code);
    if (found != null) {
      Printer.inline("Update for: ");
      Printer.newline(" | ", found.getCode(), found.getName());
      listStations();

      Track station = requestTrack();
      station.setId(found.getId());

      trackDao.update(station);
      Notification.success("Track updated.");
    } else {
      Notification.error("Track cannot be found.");
    }
  }

  @Override
  public void delete() {
    String code = Inputer.requestString("Find Code? ");

    Track found = trackDao.queryByCode(code);
    if (found != null) {
      trackDao.delete(found);
    } else {
      Notification.error("Track cannot be found");
    }
  }

  @Override
  public void find() {
    Notification.error("Unsupported feature.");
  }

  @Override
  public void all() {
    Printer.manyWithHeaders(
        Track.HEADERS_FORMAT,
        Track.HEADERS,
        trackDao.queryAll()
    );
  }

  @Override
  public void doMenu() {
    menu.doMenu();
  }

  private void listStations() {
    stationController.all();
  }

  private Track requestTrack() {
    String code             =  Inputer.requestString("Code: ");
    String name             =  Inputer.requestString("Name: ");
    String sourceCode       =  Inputer.requestString("Source code: ");
    String destinationCode  =  Inputer.requestString("Destination code: ");

    return new Track(code, name, sourceCode, destinationCode);
  }
}
