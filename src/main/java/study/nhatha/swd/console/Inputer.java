package study.nhatha.swd.console;

import java.util.Scanner;

public final class Inputer {
  private static final Scanner SCANNER = new Scanner(System.in);
  private Inputer() {
  }

  public static int requestInt() {
    return SCANNER.nextInt(10);
  }

  public static int requestInt(String label) {
    Printer.inline(label);
    return requestInt();
  }

  public static float requestFloat() {
    return SCANNER.nextFloat();
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
}
