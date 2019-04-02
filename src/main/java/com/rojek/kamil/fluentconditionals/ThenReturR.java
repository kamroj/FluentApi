package com.rojek.kamil.fluentconditionals;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Kamil Rojek
 */
public class ThenReturR<T, R>{
    private T t;
    private R r;
    private boolean condition;


    ThenReturR(T t, R r, boolean condition) {
        this.t = t;
        this.r = r;
        this.condition = condition;
    }

    public void orElse(Runnable r) {
        if(!condition)
            r.run();
    }

    public void orElse(Consumer<R> consumer) {
        if(!condition)
            consumer.accept(r);
    }

    public R orElse(Supplier<R> r) {
        return orElse(r.get());
    }

    public R orElse(Function<T, R> function) {
        return orElse(function.apply(t));
    }

    public R orElse(R r) {
        if (condition)
            return this.r;
        return r;
    }

    public <ExceptionType extends Throwable> R orElseThrow(Supplier<ExceptionType> exceptionType) throws ExceptionType {
        if (!condition)
            throw exceptionType.get();
        return r;
    }


    public <ExceptionType extends Throwable> ExceptionThrower<T, ExceptionType> orElseThrow(Supplier<ExceptionType> e,String message)  {
        return new ExceptionThrower<>(t, e.get(), condition, message);
    }

}