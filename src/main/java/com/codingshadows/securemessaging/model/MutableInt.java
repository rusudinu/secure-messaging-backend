package com.codingshadows.securemessaging.model;

import lombok.Getter;

@Getter
public class MutableInt {
    int value = 1;

    public void increment() {
        ++value;
    }

    public void decrement() {
        --value;
    }
}