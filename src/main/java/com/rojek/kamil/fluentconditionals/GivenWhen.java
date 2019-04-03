package com.rojek.kamil.fluentconditionals;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Kamil Rojek
 */
public class GivenWhen<GivenType>{
    private GivenType givenType;
    private boolean condition;

    public GivenWhen(boolean condition, GivenType givenType) {
        this.condition = condition;
        this.givenType = givenType;
    }

    public OrElseGiven<GivenType> then(Consumer<GivenType> consumer){
        return new OrElseGiven<>(condition,givenType,consumer);
    }

    public <ReturnType> OrElseGivenReturn<GivenType, ReturnType> thenReturn(Supplier<ReturnType> supplier){
        return thenReturn(givenType -> supplier.get());
    }

    public <ReturnType> OrElseGivenReturn<GivenType, ReturnType> thenReturn(Function<GivenType, ReturnType> function){
        return new <ReturnType>OrElseGivenReturn<GivenType, ReturnType>(condition, givenType, function);
    }
}
