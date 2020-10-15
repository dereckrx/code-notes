package javaNotes;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicReference;

import static org.junit.jupiter.api.Assertions.*;

class TimingTest {

    @Test
    void timed() {
        val description = "Supply carrot";
        AtomicReference<String> output = new AtomicReference<>("");

        Timing.timed(description,
            output::set,
            () -> "carrot");

        assert(output.get().contains(description));
    }

}
