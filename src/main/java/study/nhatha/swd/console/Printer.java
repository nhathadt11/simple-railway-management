package study.nhatha.swd.console;

import java.util.List;

public class Printer {
  public static void print(String format, String[] args) {
    System.out.printf(format, args);
  }

  public static void printWithHeaders(String format, String[] headers, List list) {
    print(format, headers);
    list.forEach(System.out::println);
  }
}
