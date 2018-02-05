package study.nhatha.swd.station;

import study.nhatha.swd.console.Inputer;
import study.nhatha.swd.console.Printer;
import study.nhatha.swd.generic.AppController;
import study.nhatha.swd.menu.Menu;
import study.nhatha.swd.util.Notification;

import java.util.LinkedHashMap;
import java.util.Map;

public class StationController implements AppController{
  private StationDao stationDao;
  private Menu menu;

  public StationController() {
    this.stationDao = new StationDao();
    this.initMenu();
  }

  private void initMenu() {
    Map<String, Menu.Action> actions = new LinkedHashMap<>();

    actions.put("1. Add",       StationController.this::add);
    actions.put("2. Add Many",  StationController.this::addMany);
    actions.put("3. Update",    StationController.this::update);
    actions.put("4. Delete",    StationController.this::delete);
    actions.put("5. Find",      StationController.this::find);
    actions.put("6. List",      StationController.this::all);

    menu = new Menu("STATION", actions);
  }

  @Override
  public void add() {
    stationDao.add(requestStation());
  }

  @Override
  public void addMany() {
    Notification.error("Unsupported feature.");
  }

  @Override
  public void update() {
    String code = Inputer.requestString("Find Code? ");

    Station found = stationDao.queryByCode(code);
    if (found != null) {
      Printer.inline("Update for: ");
      Printer.newline(" | ", found.getCode(), found.getName());

      Station station = requestStation();
      station.setId(found.getId());

      stationDao.update(station);
      Notification.success("Station updated.");
    } else {
      Notification.error("Station cannot be found.");
    }
  }

  @Override
  public void delete() {
    String code = Inputer.requestString("Find Code? ");

    Station found = stationDao.queryByCode(code);
    if (found != null) {
      stationDao.delete(found);
    } else {
      Notification.error("Station cannot be found");
    }
  }

  @Override
  public void find() {
    Notification.error("Unsupported feature.");
  }

  @Override
  public void all() {
    Printer.manyWithHeaders(
        Station.HEADERS_FORMAT,
        Station.HEADERS,
        stationDao.queryAll()
    );
  }

  @Override
  public void doMenu() {
    menu.doMenu();
  }

  private Station requestStation() {
    String code = Inputer.requestString("Code: ");
    String name = Inputer.requestString("Name: ");

    return new Station(code, name);
  }
}
