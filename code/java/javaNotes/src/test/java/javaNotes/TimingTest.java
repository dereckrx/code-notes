package javaNotes;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static javaNotes.Eq.eq;

class TimingTest {

    @Test
    void timed() {
        var description = "Supply carrot";
        AtomicReference<String> output = new AtomicReference<>("");

        var result = Timing.timed(description, output::set, () -> {
          // Do some stuff...
          return "carrot";
        });

        eq(result, "carrot");
        eq(output.get().matches("Supply carrot took .* milliseconds"), true);
    }

}
