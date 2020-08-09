package com.github.kovaku.domain;

public class RandomResponse {
    private final Integer value;
    private final Boolean even;

    public RandomResponse(Integer value, Boolean even) {
        this.value = value;
        this.even = even;
    }

    public Integer getValue() {
        return value;
    }

    public Boolean getEven() {
        return even;
    }
}
