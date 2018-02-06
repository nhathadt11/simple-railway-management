package study.nhatha.swd;

import study.nhatha.swd.menu.Menu;
import study.nhatha.swd.menu.SimpleMenu;
import study.nhatha.swd.station.StationController;
import study.nhatha.swd.ticket.TicketController;
import study.nhatha.swd.tour.TourController;
import study.nhatha.swd.track.TrackController;
import study.nhatha.swd.train.TrainController;

import java.util.LinkedHashMap;
import java.util.Map;


/**
 * Hello world!
 *
 */
public class App 
{
  public static void main( String[] args )
  {
    StationController stationController = new StationController();
    TrackController trackController     = new TrackController();
    TrainController trainController     = new TrainController();
    TourController tourController       = new TourController();
    TicketController ticketController   = new TicketController();

    Map<String, Menu.Action> actions = new LinkedHashMap<>();
    actions.put("1. Station"  , stationController::doMenu);
    actions.put("2. Track"    , trackController::doMenu);
    actions.put("3. Train"    , trainController::doMenu);
    actions.put("4. Tour"     , tourController::doMenu);
    actions.put("5. Ticket"   , ticketController::doMenu);

    Menu mainMenu = new SimpleMenu("MAIN", actions);
    mainMenu.doMenu();
  }
}
