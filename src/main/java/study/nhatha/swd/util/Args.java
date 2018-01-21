package study.nhatha.swd.util;

import java.util.List;

public final class Args {
  public static void requireNonNull(Object object) {
    if (object == null) {
      throw new IllegalArgumentException("Object cannot be null");
    }
  }

  private Args() {
  }
}
