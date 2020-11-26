package javaNotes.firstClassFunctions;

import java.util.function.BiFunction;
import java.util.function.Function;

public class Func {
  // Similar to Runnable, but user .apply instead of .run, and does not warn about threads
  public interface F {
    void apply();
  }

  // Similar Supplier<T>, but uses .apply instead of .get
  public interface F0<T> {
    T apply();
  }

  public interface F1<T, R> extends Function<T, R> {
  }

  // Similar to Consumer<T>, .apply instead of .accept
  public interface F1Void<T> {
    void apply(T t);
  }

  public interface F2<T, U, R> extends BiFunction<T, U, R> {
  }

  public interface F3<T, U, V, R> {
    R apply(T t, U u, V v);
  }

  @FunctionalInterface
  public interface ValueAtTime {
    double valueAt(int time);
  }
}
