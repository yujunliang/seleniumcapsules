package com.algocrafts.selenium;


import java.util.Objects;
import java.util.function.Function;
import java.util.function.Predicate;

public interface Locator<Where, What> extends Function<Where, What> {

    /**
     * locate the result on the search context.
     *
     * @param where the search context
     * @return located result
     */
    What locate(Where where);

    /**
     * Returns a composed function that first applies this function to
     * its input, and then applies the {@code after} function to the result.
     * If evaluation of either function throws an exception, it is relayed to
     * the caller of the composed function.
     *
     * @param after the function to locate after this function is applied
     * @return a composed function that first applies this function and then
     * applies the {@code after} function
     * @throws NullPointerException if after is null
     * @see #compose(Function)
     */
    default <V> Locator<Where, V> andNext(Locator<? super What, ? extends V> after) {
        Objects.requireNonNull(after);
        return (Where t) -> after.locate(locate(t));
    }

    /**
     * Returns a composed predicate that represents a short-circuiting logical
     * AND of this predicate and a locator.  When evaluating the composed
     * predicate, locating the element first and test the element with the Predicate.
     * <p>
     * <p>Any exceptions thrown during evaluation of either predicate are relayed
     * to the caller; if evaluation of this predicate throws an exception, the
     * {@code other} predicate will not be evaluated.
     *
     * @param other a predicate that will be tested with this after locating
     *              element using this locator
     * @return a predicate that represents the {@code other} predicate for
     * the element located by this
     * @throws NullPointerException if other is null
     */
    default Predicate<Where> and(Predicate<What> other) {
        Objects.requireNonNull(other);
        return (Where t) -> other.test(locate(t));
    }

    /**
     * Applies this function to the given argument by calling
     * the locate method of this locator.
     *
     * @param t the function argument
     * @return the function result
     */
    @Override
    default What apply(Where t) {
        return locate(t);
    }
}
