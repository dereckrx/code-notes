import java.util.List;

public class Collections {
    public static void print(Object obj) {
        System.out.println(obj);
    }
    public static void main(String[] args) {
        print("Hello, World 2");
        var empty = List.of();
        print(empty);
    }
}
