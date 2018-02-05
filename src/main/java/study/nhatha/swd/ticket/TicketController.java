package study.nhatha.swd.ticket;

import study.nhatha.swd.console.Inputer;
import study.nhatha.swd.console.Printer;
import study.nhatha.swd.menu.Menu;
import study.nhatha.swd.menu.SimpleMenu;
import study.nhatha.swd.tour.TourController;
import study.nhatha.swd.util.Notification;

import java.util.LinkedHashMap;
import java.util.Map;

public class TicketController implements Menu {
  private TicketDao ticketDao;
  private TourController tourController;
  private SimpleMenu menu;

  public TicketController() {
    this.ticketDao = new TicketDao();
    this.tourController = new TourController();
    this.initMenu();
  }

  private void initMenu() {
    Map<String, Menu.Action> actions = new LinkedHashMap<>();

    actions.put("1. Add",     this::add);
    actions.put("2. Update",  this::update);
    actions.put("3. Sell",    this::sell);
    actions.put("4. All",     this::all);

    menu = new SimpleMenu("TICKET", actions);
  }

  public void add() {
    printTours();
    ticketDao.add(requestTicket());
  }

  public void update() {
    String code = Inputer.requestString("Find Code? ");

    Ticket found = ticketDao.queryByCode(code);
    if (found != null) {
      Printer.inline("Update for: ");
      Printer.newline(" | ", found.getCode(), found.getName());

      Ticket ticket = requestTicket();
      ticket.setId(found.getId());

      ticketDao.update(ticket);
      Notification.success("Ticket updated.");
    } else {
      Notification.error("Ticket cannot be found.");
    }
  }

  public void sell() {
    all();
    String code = Inputer.requestString("Find Code? ");
    Ticket found = ticketDao.queryByCode(code);
    if (found != null) {
      Printer.inline("Sell: ");
      Printer.newline(" | ", found.getCode(), found.getName());

      Ticket ticket = requestFillingCustomer(found);
      ticket.setId(found.getId());

      ticketDao.update(ticket);
      Notification.success("Ticket sold.");
    } else {
      Notification.error("Ticket cannot be found.");
    }
  }

  public void all() {
    Printer.manyWithHeaders(
        Ticket.HEADERS_FORMAT,
        Ticket.HEADERS,
        ticketDao.queryAll()
    );
  }

  private void printTours() {
    tourController.all();
  }

  private Ticket requestTicket() {
    String code     = Inputer.requestString("Code: ");
    String name     = Inputer.requestString("Name: ");
    String tourCode = Inputer.requestString("Tour code: ");

    return new Ticket(code, name, tourCode);
  }

  private Ticket requestFillingCustomer(Ticket ticket) {
    String firstName        = Inputer.requestString("First name: ");
    String lastName         = Inputer.requestString("Last name: ");
    String phoneNumber      = Inputer.requestString("Phone number: ");
    String permanentAddress = Inputer.requestString("Permanent Address: ");

    ticket.setCustomer(ticket.new Customer(firstName, lastName, phoneNumber, permanentAddress));

    return ticket;
  }

  @Override
  public void doMenu() {
    menu.doMenu();
  }
}
