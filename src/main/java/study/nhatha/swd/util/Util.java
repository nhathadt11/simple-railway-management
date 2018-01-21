package study.nhatha.swd.util;

import java.util.Collection;
import java.util.List;

public final class Util {
  public static boolean isNull(Object object) {
    return object == null;
  }

  public static boolean isEmpty(Collection collection) {
    if (collection == null) {
      throw new IllegalArgumentException("Not an instance of java.util.Collection");
    }

    return collection.isEmpty();
  }

  public static boolean isEmpty(String string) {
    if (string == null) {
      throw new IllegalArgumentException("Not an instance of java.util.String");
    }

    return string.isEmpty();
  }

  public static boolean isNullOrEmpty(Collection collection) {
    return isNull(collection) || isEmpty(collection);
  }

  public static boolean isNullOrEmpty(String string) {
    return isNull(string) || isEmpty(string);
  }

  private Util() {
  }
}
