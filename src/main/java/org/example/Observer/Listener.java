package org.example.Observer;

@FunctionalInterface
public interface Listener<T> {

    void onUpdate(T value);
    
}
