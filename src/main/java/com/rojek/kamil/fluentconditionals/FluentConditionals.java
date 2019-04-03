package com.rojek.kamil.fluentconditionals;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Kamil Rojek
 */
public class FluentConditionals {
    public static Runnable doNothing = () -> {};
    public static Consumer doNothing() { return (o) -> {}; }

    public static <Type> WhenGiven<Type> given(Supplier<Type> suplier) {
        return given(suplier.get());
    }

    public static <Type> WhenGiven<Type> given(Type type) {
        return new <Type>WhenGiven<Type>(type);
    }

    public static Then when(Supplier<Boolean> supplier) {
        return when(supplier.get());
    }

    public static Then when(boolean condition) {
        return new Then(condition);
    }
}
