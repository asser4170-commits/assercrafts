package com.assercraft.registry;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

public final class Registry<T> {
    private final Map<String, T> values = new LinkedHashMap<>();

    public void register(String key, T value) {
        values.put(key, value);
    }

    public Optional<T> get(String key) {
        return Optional.ofNullable(values.get(key));
    }

    public Collection<T> all() {
        return values.values();
    }

    public int size() {
        return values.size();
    }
}
