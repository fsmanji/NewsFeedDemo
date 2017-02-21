package com.example.fsmanji.data.repository;

import java.util.HashMap;

/**
 * Created by fsmanji on 2/19/17.
 */

public class InMemoryCache<V> {
    private HashMap<String, V> store = new HashMap<>();

    public void set(String key, V value) {
        store.put(key, value);
    }

    public V get(String key) {
        return store.get(key);
    }

    public boolean contains(String key) {
        return store.containsKey(key);
    }

    public V remove(String key) {
        V value = get(key);
        if (value != null) {
            store.remove(key);
        }

        return value;
    }

    public void evictAll() {
        store.clear();
    }
}
