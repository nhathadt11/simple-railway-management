package study.nhatha.swd.util;

import study.nhatha.swd.console.Printer;

import java.sql.SQLException;

public final class Notification {
  private Notification() {
  }

  public static void success(String message) {
    Printer.newline(": ", "Success", message);
  }

  public static void error(String message) {
    Printer.newline(": ", "Error", message);
  }

  public static void error(SQLException e) {
    Printer.newlinePretty("Code: %d Message: %s", e.getErrorCode(), e.getMessage());
  }
}
