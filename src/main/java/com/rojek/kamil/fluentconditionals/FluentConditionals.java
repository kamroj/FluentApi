package com.rojek.kamil.fluentconditionals;

import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Kamil Rojek
 */
public class FluentConditionals {
    private boolean condition;
    public static Runnable doNothing = () -> {};

    public static Runnable doNothing() { return () -> {}; }

    private FluentConditionals(boolean condition) {
        this.condition = condition;
    }

    public static <T> ElseReturn<T> given(Supplier<T> stringSupplier) {
        return new ElseReturn<>(stringSupplier.get());
    }

    public static <T> ElseReturn<T> given(T t) {
        return new ElseReturn<>(t);
    }

    public static FluentConditionals when(Supplier<Boolean> supplier) {
        return new FluentConditionals(supplier.get());
    }

    public static FluentConditionals when(boolean result) {
        return new FluentConditionals(result);
    }

    public <T> ElseReturn<T> then(Runnable runnable) {
        if (condition)
            runnable.run();
        return new ElseReturn<>(condition);
    }

    public <T> ElseReturn<T> thenReturn(Supplier<T> t) {
        return new ElseReturn<>(condition, t.get());
    }

    public <T> ElseReturn<T> thenReturn(T t) {
        return new ElseReturn<>(condition, t);
    }

    public <ExceptionType extends Throwable> void thenThrow(Function<String, ExceptionType> rt, String message) throws ExceptionType {
        throw rt.apply(message);
    }
}
