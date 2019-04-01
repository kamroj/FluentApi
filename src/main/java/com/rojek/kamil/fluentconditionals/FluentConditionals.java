package com.rojek.kamil.fluentconditionals;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * @author Kamil Rojek
 */
class FluentConditionals {
    private boolean condition;
    static Runnable doNothing = () -> {};

    private FluentConditionals(boolean condition) {
        this.condition = condition;
    }

    static <T> ElseReturn<T> given(Supplier<T> stringSupplier) {
        return new ElseReturn<>(stringSupplier.get());
    }

    static <T> ElseReturn<T> given(T t) {
        return new ElseReturn<>(t);
    }

    static FluentConditionals when(Supplier<Boolean> supplier){
        return new FluentConditionals(supplier.get());
    }

    static FluentConditionals when(boolean result) {
        return new FluentConditionals(result);
    }

    <T> ElseReturn<T> then(Runnable runnable) {
        if (condition)
            runnable.run();
        return new ElseReturn<>(condition);
    }

    <T> ElseReturn<T> thenReturn(Supplier<T> t) {
        return new ElseReturn<>(condition, t.get());
    }

    <T> ElseReturn<T> thenReturn(T t) {
        return new ElseReturn<>(condition, t);
    }

    static Runnable doNothing(){ return ()->{}; }


    static class ElseReturn <T> {
        private T t;
        private boolean condition;

        ElseReturn(boolean condition) {
            this.condition = condition;
        }

        ElseReturn(T t) {
            this.t = t;
        }

        ElseReturn(boolean condition, T t) {
            this(condition);
            this.t = t;
        }

        ElseReturn<T> when(boolean condition){
            return new ElseReturn<>(condition, t);
        }

        ElseReturn<T> when(Supplier<Boolean> isTrue){
            return new ElseReturn<>(isTrue.get(), t);
        }

        ElseReturn<T> then(Consumer<T> consumer) {
            if (condition)
                consumer.accept(t);
            return new ElseReturn<>(condition, t);
        }

        void orElse(Runnable t) {
            if(!condition)
                t.run();
        }

        void orElse(Consumer<T> consumer) {
            if(!condition)
                consumer.accept(t);
        }

        T orElse(Supplier<T> t) {
            return orElse(t.get());
        }

        T orElse(T t) {
            if (condition)
                return this.t;
            return t;
        }

        T orElseThrow(Supplier<RuntimeException> e)  {
            return orElseThrow(e.get());
        }

        T orElseThrow(RuntimeException e) {
            if(!condition)
                throw e;
            return t;
        }
    }


}
