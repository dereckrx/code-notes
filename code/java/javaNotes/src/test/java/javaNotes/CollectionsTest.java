package javaNotes;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.stream.Collectors;

import static javaNotes.Printer.p;
import static org.junit.jupiter.api.Assertions.*;

class CollectionsTest {

    @Test
    void usingOf() {

        // Immutable empty
        var empty = List.of();

        // Use .of() with List, Map, or Set
        var list = List.of(1, 2, 3);
        var map = Map.of('a', 1, 'b', 2);
        var set = Set.of(1, 2, 3);

        p(map);
        p(list);
        p(set);

        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
        assertEquals(3, list.get(2));

        assertEquals(1, map.get('a'));
        assertEquals( 2,  map.get('b'));

        assertEquals(true, set.contains(1));
        assertEquals(true, set.contains(2));

        // Immutable
        assertThrows(UnsupportedOperationException.class, () -> list.add(4));
    }

    @Test
    void asList() {
        // Why use this? Is it < java 9?
        List<String> list1 =
            Arrays.asList("a", "b", "c");

        List<String> list2 = new ArrayList<>(
            Arrays.asList("a", "b", "c"));

        val abc = new String[] { "a", "b", "c" };
        List<String> list3 = Arrays.asList(abc);

        assertEquals(List.of("a", "b", "c"), list1);
        assertEquals(List.of("a", "b", "c"), list2);
        assertEquals(List.of("a", "b", "c"), list3);

        List<String> list4 = list1.stream().skip(1).collect(Collectors.toList());
        p(list4);
    }


}
