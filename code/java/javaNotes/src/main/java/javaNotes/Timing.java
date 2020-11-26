package javaNotes;

import java.util.Date;
import java.util.function.Consumer;
import java.util.function.Supplier;

/*
In Object oriented programming, we use encapsulation to hide our object internals from the outside world,
so we can hide the implementation details behind an interface.

In Functional Programming, we use isolation so that our function does not know anything about the outside world,
and can be used in any context.

I don't want the outside world to know anything about my object implementation details.
I don't want my function to know anything about the outside world.
 */

public class Timing {
  public static <A> A timed(String description,
                            Consumer<String> output,
                            Supplier<A> code) {
    var before = new Date();
    A result = code.get();
    var duration = new Date().getTime() - before.getTime();

    output.accept(description + " took " + duration + " milliseconds");

    return result;
  }
}
