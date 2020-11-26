// --- Functions ---
import java.lang.Runnable // () -> void
import java.util.concurrent.Callable; // () -> T
import java.util.function.Supplier; // () -> T
import java.util.function.Consumer; // (T) -> void
import java.util.function.Function; // (T) -> R
import java.util.function.BiFunction; // (T, U) -> R
import java.util.function.Predicate; // (T) -> Boolean
import java.util.function.BinaryOperator; // (T, T) -> T
import java.util.function.IntToDoubleFunction; // (Int) -> Double

// --- Collections ---
import java.util.List; // .of(1, 2, 3)
import java.util.Map;
import java.util.Set;
import java.util.Arrays; // Arrays.asList(1, 2, 3)
import java.util.ArrayList; // new ArrayList(list)
import java.util.stream.Collectors;
import static java.util.stream.Collectors.toList;
import static java.util.stream.Collectors.toUnmodifiableList;
