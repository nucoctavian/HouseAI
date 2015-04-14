package org.nuc.houseai.utils;

public class Container<T> {
    private T value;

    public Container() {

    }

    public Container(T initialValue) {
        this.value = initialValue;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }
}
