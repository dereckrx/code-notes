package javaNotes;

import com.google.common.base.Joiner;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toUnmodifiableList;
import static javaNotes.Eq.eq;
import static javaNotes.Printer.p;
import static org.junit.jupiter.api.Assertions.assertEquals;

/*
https://stackify.com/streams-guide-java-8/

Intermediate operations
- map, filter
- peek, skip, sorted

Terminal operations (ends the stream)
- forEach, count, limit, collect, findFirst, orElse
- anyMatch, allMatch, nonMatch
*/
class StreamsTest {

  @Test
  void reduce() {
    Integer sum1 = List.of(1, 2, 3).stream()
        .reduce(0, Integer::sum);

    Integer sum2 = List.of(1, 2, 3).stream()
        .reduce(0, (acc, i) -> acc + i);

    eq(sum1, 6);
    eq(sum2, 6);
  }

  @Test
  void stream_over_primitives() {
    int[] intArray = {1, 2, 3};

    String joined = Arrays.stream(intArray)
        .mapToObj(String::valueOf)
        .collect(Collectors.joining(" "));

    assertEquals(joined, List.of(1, 2, 3));
  }

  @Test
  void filter() {
    List<String> filtered = List.of("a", "b", "c")
        .stream()
        .peek(i -> p("This is my value: ", i))
        .filter(i -> i.equals("a"))
        .collect(Collectors.toList());
    eq(filtered, List.of("a"));

    List<String> names = List.of("Bob", "Cindy", "Alice", "Clark" )
        .stream()
        .map(String::toLowerCase)
        .filter(s -> s.startsWith("c"))
        .sorted()
        .peek(System.out::println)
        .collect(toUnmodifiableList());
    eq(names, List.of("cindy", "clark"));


    // TODO
    // Collectors.groupingBy(i -> i.store, Collectors.summarizingDouble(Store::total))
    // stream().anyMatch(i -> i > 10);
    // .limit().findFirst
    // stream().generate(() -> new Item(rand(3)))
  }

  @Test
  void joining_strings() {
    String[] strings = new String[]{"a", "b", "c"};

    var joined = Stream.of(strings).collect(Collectors.joining(" "));
    var joiner = Joiner.on(" ").join(strings);

    eq(joined, "a b c");
    eq(joiner, "a b c");
  }
}
