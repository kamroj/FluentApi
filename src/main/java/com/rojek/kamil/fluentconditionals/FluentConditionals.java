package com.rojek.kamil.fluentconditionals;

import java.util.function.Consumer;
import java.util.function.Function;
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

//        <R> ThenReturR<R, T> thenReturn(Supplier<T> t) {
//            return new ThenReturR<>( condition, t.get());
//        }

        <R> ThenReturR<T, R> thenReturn(Supplier<R> r) {
            return new ThenReturR<>(t, r.get(), condition);
        }

        <R> ThenReturR<T, R> thenReturn(Function<T, R> function) {
            return new ThenReturR<>(t, function.apply(t),condition);
        }

        ElseReturn<T> thenReturn(T t) {
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

    static class ThenReturR<T, R>{
        private T t;
        private R r;
        private boolean condition;

//        ThenReturR(boolean condition, T t) {
//            this.condition = condition;
//            this.t = t;
//            //this.r = r;
//        }

//        ThenReturR(R r, boolean condition) {
//            this.condition = condition;
//            this.r = r;
//            //this.r = r;
//        }


        ThenReturR(T t, R r, boolean condition) {
            this.t = t;
            this.r = r;
            this.condition = condition;
        }

        void orElse(Runnable r) {
            if(!condition)
                r.run();
        }

        void orElse(Consumer<R> consumer) {
            if(!condition)
                consumer.accept(r);
        }

        R orElse(Supplier<R> r) {
            return orElse(r.get());
        }

        R orElse(Function<T, R> function) {
            return orElse(function.apply(t));
        }

        R orElse(R r) {
            if (condition)
                return this.r;
            return r;
        }


    }


}
