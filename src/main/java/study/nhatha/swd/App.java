package study.nhatha.swd;

import study.nhatha.swd.menu.Menu;
import study.nhatha.swd.station.StationController;
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

    Map<String, Menu.Action> actions = new LinkedHashMap<>();
    actions.put("1. Station"  , stationController::doMenu);
    actions.put("2. Track"    , trackController::doMenu);
    actions.put("3. Train"    , trainController::doMenu);
    actions.put("4. Tour"     , tourController::doMenu);

    Menu mainMenu = new Menu("MAIN", actions);
    mainMenu.doMenu();
  }
}
