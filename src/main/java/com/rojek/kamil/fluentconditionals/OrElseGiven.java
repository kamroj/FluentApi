package com.rojek.kamil.fluentconditionals;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author Kamil Rojek
 */
public class OrElseGiven<GivenType> {
    private boolean condition;
    private GivenType givenType;
    private Consumer<GivenType> givenTypeTrueConsumer;

    OrElseGiven(boolean condition, GivenType givenType, Consumer<GivenType> givenTypeTrueConsumer) {
        this.condition = condition;
        this.givenType = givenType;
        this.givenTypeTrueConsumer = givenTypeTrueConsumer;
    }

    public void orElse(Consumer<GivenType> consumer){
        if(!condition) {
            consumer.accept(givenType);
        } else {
            givenTypeTrueConsumer.accept(givenType);
        }
    }


    public <ExceptionType extends Throwable> void orElseThrowE(ExceptionType exceptionType) throws ExceptionType {
        orElseThrow(() -> exceptionType); }

    public <ExceptionType extends Throwable> void orElseThrow(Function<String, ExceptionType> exceptionType, String message) throws ExceptionType {
        orElseThrow(() -> exceptionType.apply(message));
    }

    public <ExceptionType extends Throwable> void orElseThrow(Supplier<ExceptionType> exceptionType) throws ExceptionType {
        if(!condition)
            throw exceptionType.get();
        givenTypeTrueConsumer.accept(givenType);
    }
}
