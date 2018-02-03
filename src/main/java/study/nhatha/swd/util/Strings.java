package study.nhatha.swd.util;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class Strings {
  public static String makeJoin(String token, int number, String delimiter) {
    String[] strings = new String[number];
    Arrays.fill(strings, token);

    return Stream.of(strings).collect(Collectors.joining(delimiter));
  }
  private Strings() {
  }
}
