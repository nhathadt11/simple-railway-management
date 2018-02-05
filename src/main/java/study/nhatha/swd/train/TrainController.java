package study.nhatha.swd.train;

import study.nhatha.swd.console.Inputer;
import study.nhatha.swd.console.Printer;
import study.nhatha.swd.generic.AppController;
import study.nhatha.swd.menu.Menu;
import study.nhatha.swd.menu.SimpleMenu;
import study.nhatha.swd.util.Notification;

import java.util.LinkedHashMap;
import java.util.Map;

public class TrainController implements AppController {
  private TrainDao trainDao;
  private SimpleMenu menu;

  public TrainController() {
    this.trainDao = new TrainDao();
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

    menu = new SimpleMenu("TRAIN", actions);
  }

  @Override
  public void add() {
    trainDao.add(requestTrain());
  }

  @Override
  public void addMany() {
    Notification.error("Unsupported feature.");
  }

  @Override
  public void update() {
    String code = Inputer.requestString("Find Code? ");

    Train found = trainDao.queryByCode(code);
    if (found != null) {
      Printer.inline("Update for: ");
      Printer.newline(" | ", found.getCode(), found.getName());

      Train train = requestTrain();
      train.setId(found.getId());

      trainDao.update(train);
      Notification.success("Train updated.");
    } else {
      Notification.error("Train cannot be found.");
    }
  }

  @Override
  public void delete() {
    String code = Inputer.requestString("Find Code? ");

    Train found = trainDao.queryByCode(code);
    if (found != null) {
      trainDao.delete(found);
    } else {
      Notification.error("Train cannot be found");
    }
  }

  @Override
  public void find() {
    Notification.error("Unsupported feature.");
  }

  @Override
  public void all() {
    Printer.manyWithHeaders(
        Train.HEADERS_FORMAT,
        Train.HEADERS,
        trainDao.queryAll()
    );
  }

  @Override
  public void doMenu() {
    menu.doMenu();
  }

  private Train requestTrain() {
    String code       = Inputer.requestString("Code: ");
    String name       = Inputer.requestString("Name: ");
    int seatNum       = Inputer.requestInt("Seat Num: ");
    float seatPrice   = Inputer.requestFloat("Seat Price: ");

    return new Train(code, name, seatNum, seatPrice);
  }
}
