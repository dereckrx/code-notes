package javaNotes;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static javaNotes.Eq.eq;
import static org.junit.jupiter.api.Assertions.assertThrows;

//https://www.baeldung.com/java-immutable-list
//https://www.baeldung.com/java-combine-multiple-collections
class CollectionsTest {

  @Test
  void Collection_of() {

    // Immutable empty
    var empty = List.of();
    eq(empty.size(), 0);

    // Use of() with List, Map, or Set
    var list = List.of(1, 2, 3);
    var map = Map.of('a', 1, 'b', 2);
    var set = Set.of(1, 2, 3);

    // Use of() with arrays
    Integer[] intArray = new Integer[]{1, 2, 3};
    var integerList = List.of(intArray);
    eq(integerList, list);

    eq(list.get(0), 1);
    eq(list.get(1), 2);
    eq(list.get(2), 3);

    eq(map.get('a'), 1);
    eq(map.get('b'), 2);

    eq(set.contains(1), true);
    eq(set.contains(2), true);

    // Immutable
    assertThrows(UnsupportedOperationException.class, () -> list.add(4));

    // To array
    Integer[] integerArray = list.toArray(new Integer[list.size()]);
    eq(integerArray[0], 1);
  }

  @Test
  void of_primitives() {
    int[] intArray = {1, 2, 3};

    List<Integer> intList = Arrays.stream(intArray)
        .mapToObj(Integer::valueOf)
        .collect(Collectors.toList());

    eq(intList, List.of(1,2,3));

    // And back
    int[] intArray2 = intList.stream().mapToInt(Integer::intValue).toArray();
    eq(intArray2[0], intArray[0]);
  }

  @Test
  void Array_asList_for_java8() {
    // Why use this? Is it < java 9?
    List<String> abcList = Arrays.asList("a", "b", "c");

    // or you need mutability
    ArrayList<String> abcArrayList = new ArrayList(abcList);
    abcArrayList.add("d");

    eq(abcList, List.of("a", "b", "c"));
    eq(abcArrayList, List.of("a", "b", "c", "d"));
  }
}
