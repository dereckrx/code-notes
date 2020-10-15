package javaNotes;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static javaNotes.Printer.p;
import static org.junit.jupiter.api.Assertions.*;

class StreamsTest {

    @Test
    void foo() {
        int[] intArray = {1, 2, 3};
        Integer[] integerArray = new Integer[] { 1, 2, 3};

        List<int[]> intList = Arrays.asList(intArray);
        List<Integer> integerList = Arrays.asList(integerArray);

        String joined = Arrays.stream(intArray)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining(" "));

        String joined2 = Arrays.asList(integerArray)
            .stream()
            .map(Object::toString)
            .collect(Collectors.joining(" "));

        assertEquals(joined, joined2);

    }

    @Test
    void streams2() {
        //    val a = Collectors.toList();
        //    val b = Collectors.toCollection();

        List<String> list = List.of("a", "b", "c");

        List<String> filtered = list.stream()
            .peek(i -> p("This is my value: ", i))
            .filter(i -> i == "a")
            .collect(Collectors.toList());

        List<String> upper = list
            .stream()
            .filter(s -> s.startsWith("c"))
            .map(String::toUpperCase)
            .sorted()
            .peek(System.out::println)
            .collect(Collectors.toUnmodifiableList());


        // TODO
        // Collectors.groupingBy(i -> i.store, Collectors.summarizingDouble(Store::total))
        // stream().anyMatch(i -> i > 10);
        // .limit() .findFirst
        // stream().generate(() -> new Item(rand(3)))

    }

    @Test
    void terminations() {
        
    }

    public static String[] spread(List<String> list) {
        return list.toArray(new String[list.size()]);
    }
}
