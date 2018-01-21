package study.nhatha.swd;

import study.nhatha.swd.console.Printer;
import study.nhatha.swd.station.StationRepository;
import study.nhatha.swd.util.Database;
import java.util.Objects;


/**
 * Hello world!
 *
 */
public class App 
{
  public static void main( String[] args )
  {
    Objects.requireNonNull(Database.getConnection(), "Problem connecting to server");
    System.out.println("Connection established");
    StationRepository stationRepository = new StationRepository();
    Printer.printWithHeaders(
        "%-10s %-10s %s%n",
        new String[] {"Id", "Code", "Name"},
        stationRepository.queryAll()
    );
  }
}
