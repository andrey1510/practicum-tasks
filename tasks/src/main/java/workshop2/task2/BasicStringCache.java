package workshop2.task2;

import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

public class BasicStringCache {
    private final ConcurrentHashMap<String, String> cache = new ConcurrentHashMap<>();

    public void put(String key, String value) {
        cache.put(key, value);
    }

    public String get(String key) {
        return cache.get(key);
    }

    public String get(String key, Function<String, String> dbFunction) {
        return cache.computeIfAbsent(key, dbFunction);
    }

    public void remove(String key) {
        cache.remove(key);
    }

    public int size() {
        return cache.size();
    }

    public void clear() {
        cache.clear();
    }
}

