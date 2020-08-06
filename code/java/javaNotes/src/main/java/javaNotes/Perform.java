package javaNotes;

import javaNotes.firstClassFunctions.FunctionTypes.F0;
import javaNotes.firstClassFunctions.FunctionTypes.F1;
import javaNotes.firstClassFunctions.FunctionTypes.F2;
import lombok.SneakyThrows;
import lombok.val;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

public class Perform {

    interface Result<T> {
        T getValue();
        Throwable getError();
        boolean isError();
    }

    class Success<T> implements Result<T> {
        T value;

        public Success(T t) {
            this.value = t;
        }

        public T getValue() { return this.value; }
        public boolean isError() { return false; }
        public Throwable getError() { return null; }
    }

    class Failure<T> implements Result<T> {
        Throwable error;

        public Failure(Throwable t) {
            this.error = t;
        }

        T value() { return null; }
        public boolean isError() { return true; }
        public Throwable getError() { return this.error; }
        public T getValue() { return null; }
    }

    public Result perform(Supplier doBlock) {
        try {
            return new Success(doBlock.get());
        } catch(Exception e) {
            return new Failure(e);
        }
    }

    @SneakyThrows
    public <R> R unwrap(Result<R> r) {
        if(r.isError()) {
            throw r.getError();
        } else {
            return r.getValue();
        }
    }

    public void performExample() {
        F0<Result<Integer>>
            getValue = () -> new Success(1);
        F2<Integer, Integer, Result<Integer>>
            addTwo = (a, b) -> new Success(a + b);
        F1<Integer, Result<Integer>>
            validate = (a) -> new Success(a);

        val result = perform(() -> {
            val a = unwrap(getValue.apply());
            val b = unwrap(addTwo.apply(a, 2));
            val c = unwrap(validate.apply(b));
            return c;
        });

        result.isError();
    }
}
