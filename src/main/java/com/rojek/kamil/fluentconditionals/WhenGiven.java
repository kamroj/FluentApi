package com.rojek.kamil.fluentconditionals;

import java.util.function.Supplier;

/**
 * @author Kamil Rojek
 */
public class WhenGiven<GivenType> {

    private GivenType givenType;

    public WhenGiven(GivenType givenType) {
        this.givenType = givenType;
    }

    public GivenWhen<GivenType> when (Supplier<Boolean> supplier) {
        return when(supplier.get());
    }

    public GivenWhen<GivenType> when (boolean condition) {
        return new GivenWhen<>(condition, givenType);
    }
}
