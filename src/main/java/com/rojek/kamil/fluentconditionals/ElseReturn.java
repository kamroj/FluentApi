package com.rojek.kamil.fluentconditionals;

import java.util.function.Supplier;

/**
 * @author Kamil Rojek
 */
public class ElseReturn <T>{
    private boolean condition;
    private T t;

    ElseReturn(boolean condition, T t) {
        this.condition = condition;
        this.t = t;
    }

    ElseReturn thenReturn(Supplier<T> t) {
        if(condition)
            this.t = t.get();
        return new ElseReturn<>(condition, t.get());
    }

    ElseReturn<T> thenReturn(T t) {
        if(condition)
            this.t = t;
        return new ElseReturn<>(condition, null);
    }

    void orElse(Runnable r) {
        r.run();
    }

    T orElse(Supplier<T> t) {
        return orElse(t.get());
    }

    T orElse(T t) {
        if (condition)
            return this.t;
        return t;
    }

    SomeClass orElseThrow(Supplier<RuntimeException> e)  {
        if(!condition)
            throw e.get();
        return null;
    }

    void orElseThrow(RuntimeException e) {
        if(!condition)
            throw e;
    }
}
