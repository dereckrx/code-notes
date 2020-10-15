package javaNotes.firstClassFunctions;

import java.util.concurrent.Callable;
import java.util.function.*;

public class FunctionTypes {

    // Similar to Runnable, but user .apply instead of .run, and does not warn about threads
    public interface F {
        void apply();
    }
    // Similar Supplier<T>, but uses .apply instead of .get
    public interface F0<T> {
        T apply();
    }
    public interface F1<T,R> extends Function<T,R> {}

    // Similar to Consumer<T>, .apply instead of .accept
    public interface F1Void<T> {
        void apply(T t);
    }
    public interface F2<T, U, R> extends BiFunction<T,U,R> {}

    public interface F3<T, U, V, R> {
        R apply(T t, U u, V v);
    }

    public static void types() {

        /* TODO
        same apply interface for all?
        what does vavr and functioanl java do?
        */

        // () -> void
        Runnable sayHello = () -> System.out.println("Hello world");
        F sayHello2 = () -> System.out.println("Hello world!");

        sayHello.run();
        sayHello2.apply();

        // () -> result
        Supplier<Integer> getOne = () -> 1;
        Callable<Integer> getOne3 = () -> 1;
        F0<Integer> getOne2 = () -> 1;

        getOne.get();
        try {
            getOne3.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        getOne2.apply();

        // (a) -> void
        Consumer<Integer> print = number -> System.out.println(number);
        F1<Integer, Void> print2 = number -> {System.out.println(number); return null; };
        F1Void<Integer> print3 = number -> System.out.println(number);

        print.accept(3);
        print2.apply(3);
        print3.apply(3);

        // (a) -> result
        Function<Integer, Double> intToDouble2 = number -> number * 1.0;
        IntToDoubleFunction intToDouble = number -> number * 1.0;
        F1<Integer, Double> intToDoubleF = number -> number * 1.0;

        intToDouble.applyAsDouble(3);
        intToDouble2.apply(3);
        intToDoubleF.apply(3);

        // (a, b) -> result
        BiFunction<Integer, Float, String> sum = (a, b) -> a.toString() + b.toString();
        BinaryOperator<Integer> sum2 = (a, b) -> a + b;
        F2<Integer, Integer, Integer> sum3 = (a, b) -> a + b;

        sum.apply(3, 4f);
        sum2.apply(3, 4);
        sum3.apply(3, 4);

        // (a, b, c) -> result
        F3<Integer, Float, Double, String> bar = (a, b, c) -> a.toString() + b.toString() + c.toString();
        bar.apply(1, 2.0f, 3.0);

        // (a) -> boolean
        Predicate<Integer> isOne = number -> number == 1;
        Function<Integer, Boolean> isOne2 = number -> number == 1;
        F1<Integer, Boolean> isOne3 = number -> number == 1;

        isOne.test(3);
        isOne2.apply(3);
        isOne3.apply(3);
    }
}
