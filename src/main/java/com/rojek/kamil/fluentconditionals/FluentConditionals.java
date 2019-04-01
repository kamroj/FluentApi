package com.rojek.kamil.fluentconditionals;

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


    class ElseReturn <T> extends FluentConditionals{
        private T t;

        ElseReturn(boolean condition) {
            super(condition);
        }

        ElseReturn(boolean condition, T t) {
            this(condition);
            this.t = t;
        }

        void orElse(Runnable t) {
            t.run();
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
