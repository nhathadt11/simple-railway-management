package study.nhatha.swd.console;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.Scanner;

public final class Inputer {
  private static final Scanner SCANNER = new Scanner(System.in);
  private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yy-MM-dd HH:mm");
  private Inputer() {
  }

  public static int requestInt() {
    return Integer.valueOf(SCANNER.nextLine().trim());
  }

  public static int requestInt(String label) {
    Printer.inline(label);
    return requestInt();
  }

  public static float requestFloat() {
    return Float.valueOf(SCANNER.nextLine().trim());
  }

  public static float requestFloat(String label) {
    Printer.inline(label);
    return requestFloat();
  }

  public static String requestString() {
    return SCANNER.nextLine();
  }

  public static String requestString(String label) {
    Printer.inline(label);
    return requestString();
  }

  public static Date requestDate() {
    Date date = null;

    try {
      date = new Date(DATE_FORMAT.parse(SCANNER.nextLine().trim()).getTime());
    } catch (ParseException e) {
      Notification.error(e.getMessage());
    }

    return date;
  }

  public static Date requestDate(String label) {
    Printer.inline(label);
    return requestDate();
  }
}
