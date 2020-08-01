package javaNotes.firstClassFunctions;

import java.util.concurrent.Callable;
import java.util.function.*;

public class FunctionTypes {

    public interface F {
        public void apply();
    }

    public interface F0<R> {
        public R apply();
    }

    public interface F1<T,R> {
        public R apply(T t);
    }

    public interface F2<T, U, R> {
        public R apply(T t, U u);
    }

    public interface F3<T, U, V, R> {
        public R apply(T t, U u, V v);
    }

    public static void types() {

        // () -> void
        Runnable sayHello = () -> System.out.println("Hello world");
        F sayHello2 = () -> System.out.println("Hello world!");

        // () -> result
        Supplier<Integer> getOne = () -> 1;
        Callable<Integer> getOne3 = () -> 1;
        F0<Integer> getOne2 = () -> 1;

        // (a) -> void
        Consumer<Integer> print = number -> System.out.println(number);
        F1<Integer, Void> print2 = number -> {System.out.println(number); return null; };

        // (a) -> result
        Function<Integer, Double> intToDouble2 = number -> number * 1.0;
        IntToDoubleFunction intToDouble = number -> number * 1.0;
        F1<Integer, Double> intToDouble3 = number -> number * 1.0;

        Function<Integer, String> charCount = number -> number.toString();

        // (a, b) -> result
        BiFunction<Integer, Integer, Integer> sum = (a, b) -> a + b;
        BinaryOperator<Integer> sum2 = (a, b) -> a + b;
        F2<Integer, Integer, Integer> sum3 = (a, b) -> a + b;

        // (a, b, c) -> result
        F3<Integer, Float, Double, String> bar = (a, b, c) -> a.toString() + b.toString() + c.toString();

        // (a) -> boolean
        Predicate<Integer> isOne = number -> number == 1;
        Function<Integer, Boolean> isOne2 = number -> number == 1;
        F1<Integer, Boolean> isOne3 = number -> number == 1;

    }
}
