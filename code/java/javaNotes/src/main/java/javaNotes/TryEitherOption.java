package javaNotes;

import lombok.SneakyThrows;
import lombok.val;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/*
sources:
- https://xebia.com/blog/try-option-or-either/
 */
public class TryEitherOption {
    /*
    Option[T]
    - use it when you don’t care about the exact cause
    - null or absent values, or failed validation can fail
    - Getting some data that might not be there,
    - Validation logic
    Either[L,R] or Result[Success | Failure]
    - similar use case as Option but when you do need to provide some information about the error.
    Try[T]
    - use when something Exceptional can happen that you cannot handle in the function.
    - This, in general, excludes validation logic and data retrieval failures but can be used to report unexpected failures.
     */
}
