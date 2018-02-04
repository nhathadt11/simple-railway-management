package study.nhatha.swd.ticket;

import study.nhatha.swd.console.Inputer;
import study.nhatha.swd.console.Printer;
import study.nhatha.swd.menu.Menu;
import study.nhatha.swd.tour.TourController;
import study.nhatha.swd.util.Notification;

import java.util.LinkedHashMap;
import java.util.Map;

public class TicketController implements Menu.Doable {
  private TicketRepository ticketRepository;
  private TourController tourController;
  private Menu menu;

  public TicketController() {
    this.ticketRepository = new TicketRepository();
    this.tourController = new TourController();
    this.initMenu();
  }

  private void initMenu() {
    Map<String, Menu.Action> actions = new LinkedHashMap<>();

    actions.put("1. Add",    this::add);
    actions.put("2. Update", this::update);
    actions.put("3. Sell",   this::sell);
    actions.put("4. All",   this::all);

    menu = new Menu("TICKET", actions);
  }

  public void add() {
    printTours();
    ticketRepository.add(requestTicket());
  }

  public void update() {
    String code = Inputer.requestString("Find Code? ");

    Ticket found = ticketRepository.queryByCode(code);
    if (found != null) {
      Printer.inline("Update for: ");
      Printer.newline(" | ", found.getCode(), found.getName());

      Ticket ticket = requestTicket();
      ticket.setId(found.getId());

      ticketRepository.update(ticket);
      Notification.success("Ticket updated.");
    } else {
      Notification.error("Ticket cannot be found.");
    }
  }

  public void sell() {
    all();
    String code = Inputer.requestString("Find Code? ");
    Ticket found = ticketRepository.queryByCode(code);
    if (found != null) {
      Printer.inline("Sell: ");
      Printer.newline(" | ", found.getCode(), found.getName());

      Ticket ticket = requestFillingCustomer(found);
      ticket.setId(found.getId());

      ticketRepository.update(ticket);
      Notification.success("Ticket sold.");
    } else {
      Notification.error("Ticket cannot be found.");
    }
  }

  public void all() {
    Printer.manyWithHeaders(
        Ticket.HEADERS_FORMAT,
        Ticket.HEADERS,
        ticketRepository.queryAll()
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
