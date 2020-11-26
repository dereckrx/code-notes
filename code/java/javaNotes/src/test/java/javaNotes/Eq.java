package javaNotes;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Eq {
  public static <T> void eq(T a, T b) {
    assertEquals(b, a);
  }
}
