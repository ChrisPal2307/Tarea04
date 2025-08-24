package org.example.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class Observable<T> {
    private List<Listener<T>> listeners = new ArrayList<>();

    public void addListener(Listener<T> listener) {
        listeners.add(listener);
    }

    public void removeListener(Listener<T> listener) {
        listeners.remove(listener);
    }

    protected void notifyListeners(T value) {
        for (Listener<T> listener : listeners) {
            listener.onUpdate(value);
        }
    }
}
