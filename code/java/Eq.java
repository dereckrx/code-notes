// Run with $ java Eq.java

import java.util.Map;
import java.util.List;
import java.util.function.Consumer;

class Eq {
  public static <T> void eq(T a, T b) {
    if (a == null && b == null || a.equals(b)) {
      System.out.println(".");
    } else {
      System.out.println("FAIL: "+a+" == "+b+"\t"+(new Exception()).getStackTrace()[1]);
    }
  }

  public static void main(String args[]) {
    eq(null, null);
    eq(null, 1);
    eq(1, 1);
    eq(1, 2);
    eq(1.0, 1);
    eq("hello", "hello");
    eq(List.of(1, 2, 3), List.of(1, 2, 3));
    eq(Map.of("a", 1, "b", List.of(1, 2)), Map.of("a", 1, "b", List.of(1, 2)));
    eq(Map.of("a", 1, "b", List.of(1, 2)), Map.of("a", 1, "b", List.of(1, 2, 3)));
  }
}
