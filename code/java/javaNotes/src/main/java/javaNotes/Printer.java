package javaNotes;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Printer {
    public static void p(Object... objs) {
        System.out.println(Stream.of(objs).map(Object::toString).collect(Collectors.joining(" ")));
    }
}
