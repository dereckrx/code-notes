package javaNotes;

import javaNotes.firstClassFunctions.Func.*;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.IntToDoubleFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirstClassFunctionsTest {
  // TODO
  // same apply interface for all?
  // what does vavr and functioanl java do?

  @Test
  void F_void() {
    // () -> void
    List list = new ArrayList();
    Runnable add = () -> list.add("Hello Runnable");
    F addF = () -> list.add("Hello F!");

    add.run();
    eq(list, List.of("Hello Runnable"));

    addF.apply();
    eq(list, List.of("Hello Runnable", "Hello F!"));
  }

  @Test
  void F() {
    // () -> result
    Supplier<Integer> getOne = () -> 1;
    F0<Integer> getOneF = () -> 1;
    Callable<Integer> callableGetOne = () -> 1;

    eq(getOne.get(), 1);
    eq(getOneF.apply(), 1);

    // Has to be wrapped in try/catch
    try {
      callableGetOne.call();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  void F1_void() {
    // (a) -> void
    List list = new ArrayList();

    Consumer<Integer> appender = number -> list.add(number);

    F1Void<Integer> appenderFVoid = number -> list.add(number);

    F1<Integer, Void> appenderF = number -> {
      list.add(number);
      return null;
    };

    appender.accept(3);
    eq(list, List.of(3));

    appenderFVoid.apply(4);
    eq(list, List.of(3, 4));

    appenderF.apply(5);
    eq(list, List.of(3, 4, 5));
  }

  @Test
  void F1() {
    // (a) -> result
    Function<Integer, Double> intToDouble2 = number -> number * 1.0;
    IntToDoubleFunction intToDouble = number -> number * 1.0;
    F1<Integer, Double> intToDoubleF = number -> number * 1.0;

    eq(intToDouble.applyAsDouble(3), 3.0);
    eq(intToDouble2.apply(3), 3.0);
    eq(intToDoubleF.apply(3), 3.0);
  }

  @Test
  void F2() {
    // (a, b) -> result
    BiFunction<Integer, Float, Float> sum = (a, b) -> a + b;
    BinaryOperator<Integer> sum2 = (a, b) -> a + b;
    F2<Integer, Integer, Integer> sum3 = (a, b) -> a + b;

    eq(sum.apply(3, 4f), 7f);
    eq(sum2.apply(3, 4), 7);
    eq(sum3.apply(3, 4), 7);
  }

  @Test
  void F3() {
    // (a, b, c) -> result
    F3<Integer, Float, Double, String> join = (a, b, c) -> a.toString() + b.toString() + c.toString();
    eq(join.apply(1, 2.0f, 3.0), "12.03.0");
  }

  @Test
  void F_booleans() {
    // (a) -> boolean
    Predicate<Integer> isOne = number -> number == 1;
    Function<Integer, Boolean> isOne2 = number -> number == 1;
    F1<Integer, Boolean> isOne3 = number -> number == 1;

    eq(isOne.test(3), false);
    eq(isOne2.apply(3), false);
    eq(isOne3.apply(1), true);
  }

  @Test
  void functionalInterface() {
    ValueAtTime fn = (time) -> List.of(1.0, 2.0, 3.0).get(time);
    eq(fn.valueAt(1), 2.0);
  }

  <T> void eq(T a, T b) {
    assertEquals(a, b);
  }

}
