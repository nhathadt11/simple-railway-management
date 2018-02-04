package study.nhatha.swd;

import study.nhatha.swd.menu.Menu;
import study.nhatha.swd.station.StationController;
import study.nhatha.swd.track.TrackController;

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
    TrackController trackController = new TrackController();

    Map<String, Menu.Action> actions = new LinkedHashMap<>();
    actions.put("1. Station"  , stationController::doMenu);
    actions.put("2. Track"    , trackController::doMenu);

    Menu mainMenu = new Menu("MAIN", actions);
    mainMenu.doMenu();
  }
}
