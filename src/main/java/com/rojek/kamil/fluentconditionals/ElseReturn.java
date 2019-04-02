package com.rojek.kamil.fluentconditionals;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Kamil Rojek
 */
public class ElseReturn <T> {
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

    public ElseReturn<T> when(boolean condition){
        return new ElseReturn<>(condition, t);
    }

    public ElseReturn<T> when(Supplier<Boolean> isTrue){
        return new ElseReturn<>(isTrue.get(), t);
    }

    public ElseReturn<T> then(Consumer<T> consumer) {
        if (condition)
            consumer.accept(t);
        return new ElseReturn<>(condition, t);
    }

    public <R> ThenReturR<T, R> thenReturn(Supplier<R> r) {
        return new ThenReturR<>(t, r.get(), condition);
    }

    public <R> ThenReturR<T, R> thenReturn(Function<T, R> function) {
        return new ThenReturR<>(t, function.apply(t),condition);
    }

    public void orElse(Runnable t) {
        if(!condition)
            t.run();
    }

    public void orElse(Consumer<T> consumer) {
        if(!condition)
            consumer.accept(t);
    }

    public T orElse(Supplier<T> t) {
        return orElse(t.get());
    }

    public T orElse(T t) {
        if (condition)
            return this.t;
        return t;
    }


    public <ExceptionType extends Throwable> T orElseThrow(Supplier<ExceptionType> exceptionType) throws ExceptionType {
        if (!condition)
            throw exceptionType.get();
        return t;
    }

    public <ExceptionType extends Throwable> T orElseThrowE(ExceptionType exceptionType) throws ExceptionType {
        if (!condition)
            throw exceptionType;
        return t;
    }

    public <ExceptionType extends Throwable> T orElseThrow(Function<String, ExceptionType> rt, String message) throws ExceptionType {
        if (!condition)
            throw rt.apply(message);
        return t;
    }

}
