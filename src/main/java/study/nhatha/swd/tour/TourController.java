package study.nhatha.swd.tour;

import study.nhatha.swd.console.Inputer;
import study.nhatha.swd.console.Printer;
import study.nhatha.swd.generic.AppController;
import study.nhatha.swd.menu.Menu;
import study.nhatha.swd.menu.SimpleMenu;
import study.nhatha.swd.track.TrackController;
import study.nhatha.swd.train.TrainController;
import study.nhatha.swd.util.Notification;

import java.sql.Date;
import java.util.LinkedHashMap;
import java.util.Map;

public class TourController implements AppController {
  private TrackController trackController;
  private TrainController trainController;
  private TourDao tourDao;
  private SimpleMenu menu;

  public TourController() {
    this.trackController  = new TrackController();
    this.trainController  = new TrainController();
    this.tourDao = new TourDao();
    this.initMenu();
  }

  private void initMenu() {
    Map<String, Menu.Action> actions = new LinkedHashMap<>();

    actions.put("1. Add",       this::add);
    actions.put("2. Add Many",  this::addMany);
    actions.put("3. Update",    this::update);
    actions.put("4. Delete",    this::delete);
    actions.put("5. Find",      this::find);
    actions.put("6. List",      this::all);

    menu = new SimpleMenu("TOUR", actions);
  }

  @Override
  public void add() {
    printTracks();
    printTrains();
    tourDao.add(requestTour());
    Notification.success("Tour added.");
  }

  @Override
  public void addMany() {
    Notification.error("Unsupported feature.");
  }

  @Override
  public void update() {
    String code = Inputer.requestString("Find Code? ");

    Tour found = tourDao.queryByCode(code);
    if (found != null) {
      printTracks();
      printTrains();
      Printer.inline("Update for: ");
      Printer.newline(" | ", found.getCode(), found.getName());

      Tour tour = requestTour();
      tour.setId(found.getId());

      tourDao.update(tour);
      Notification.success("Tour updated.");
    } else {
      Notification.error("Tour cannot be found.");
    }
  }

  @Override
  public void delete() {
    String code = Inputer.requestString("Find Code? ");

    Tour found = tourDao.queryByCode(code);
    if (found != null) {
      tourDao.delete(found);
    } else {
      Notification.error("Tour cannot be found");
    }
  }

  @Override
  public void find() {
    Notification.error("Unsupported feature.");
  }

  @Override
  public void all() {
    Printer.manyWithHeaders(
        Tour.HEADERS_FORMAT,
        Tour.HEADERS,
        tourDao.queryAll()
    );
  }

  @Override
  public void doMenu() {
    menu.doMenu();
  }

  private Tour requestTour() {
    String code       = Inputer.requestString("Code: ");
    String name       = Inputer.requestString("Name: ");
    String trackCode  = Inputer.requestString("Track code: ");
    String trainCode  = Inputer.requestString("Train code: ");
    Date timeStart    = Inputer.requestDate("Time start: ");
    Date timeEnd      = Inputer.requestDate("Time end: ");

    return new Tour(code, name, trackCode, trainCode, timeStart, timeEnd);
  }

  private void printTracks() {
    trackController.all();
  }

  private void printTrains() {
    trainController.all();
  }
}
