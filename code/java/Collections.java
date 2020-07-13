import java.util.*;

public class Collections {
    public static void p(Object obj) {
        System.out.println(obj);
    }

    public static void main(String[] args) {
      collections();
    }

  // Using Java 9 Factory of() method
  // In Java,
  public static void collections() {

      // Use .of() with List, Map, or Set
      var map = Map.of('a', 1, 'b', 2);
      var list = List.of(1, 2, 3);
      var set = Set.of(1, 2, 3);

      p(set);
      p(map);
      p(list);

      // Immutable empty
      var empty = List.of();

      List<String> arrayList = new ArrayList<>(
          Arrays.asList("Geeks", "For", "Geeks"));
      p(arrayList);

      List<String> list2 = list1.stream().skip(1).collect(Collectors.toList())
      p(list2);

      //https://www.baeldung.com/java-immutable-list
      //https://www.baeldung.com/java-combine-multiple-collections

    }
}
