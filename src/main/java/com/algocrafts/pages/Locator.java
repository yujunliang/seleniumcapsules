package com.algocrafts.pages;


import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Locator<Where, What> extends Function<Where, What> {

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param <V>   the type of output of the {@code after} function, and of the
     *              composed function
     * @param after the function to locate after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     * @see #compose(Function)
     */
    default <V> Locator<Where, V> and(Locator<? super What, ? extends V> after) {
        Objects.requireNonNull(after);
        return (Where t) -> after.locate(locate(t));
    }

    default Predicate<Where> and(Predicate<What> other) {
        Objects.requireNonNull(other);
        return (Where t) -> other.test(locate(t));
    }

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    What locate(Where t);

    /**
     * Applies this function to the given argument.
     *
     * @param t the function argument
     * @return the function result
     */
    default What apply(Where t) {
        return locate(t);
    }
}
