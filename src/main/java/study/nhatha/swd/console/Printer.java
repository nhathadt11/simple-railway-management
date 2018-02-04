package study.nhatha.swd.console;

import study.nhatha.swd.util.Args;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Printer {
  private Printer() {
  }

  public static void inlinePretty(String format, String[] args) {
    System.out.printf(format, args);
  }

  public static void newlinePretty(String format, Object... args) {
    System.out.printf(format + "%n", args);
  }

  public static void manyWithHeaders(String format, String[] headers, List list) {
    newlinePretty(format, headers);
    list.forEach(System.out::println);
  }

  public static void inline(String message) {
    System.out.print(message);
  }

  public static void inline(String delimiter, String... messages) {
    System.out.print(joining(delimiter, messages));
  }

  public static void newline(String message) {
    System.out.println(message);
  }

  public static void newline(String delimiter, String... messages) {
    System.out.println(joining(delimiter, messages));
  }

  private static String joining(String delimiter, String... messages) {
    return Stream.of(messages).collect(Collectors.joining(delimiter));
  }
}
