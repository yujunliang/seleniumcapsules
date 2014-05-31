package com.algocrafts.objectcache;


import java.util.Collection;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class SelfPopulatingCache<K, V> {

    private final int maxSize;
    private final OneKeyCachePolicy<K> cachePolicy;
    private final Map<K, V> cache = newHashMap();
    private final Creator<K, V> creator;

    public static <K, V> SelfPopulatingCache<K, V> create(Creator<K, V> creator) {
        return new SelfPopulatingCache<>(creator);
    }

    public SelfPopulatingCache(Creator<K, V> creator) {
        this(10, new OneKeyLFUPolicy<>(), creator);
    }

    public SelfPopulatingCache(int maxSize, OneKeyCachePolicy<K> cachePolicy, Creator<K, V> creator) {
        this.maxSize = maxSize;
        this.cachePolicy = cachePolicy;
        this.creator = creator;
    }

    public synchronized V valueOf(K key) {
        K evict = cachePolicy.evictingKey(cache.size() == maxSize, key);
        if (evict != null) {
            cache.remove(evict);
        }
        V value = cache.get(key);
        if (value == null) {
            value = creator.create(key);
            if (value != null) {
                cache.put(key, value);
            }
        }
        return value;
    }

    public void remove(K key) {
        cache.remove(key);
    }

    public Collection<V> values() {
        return cache.values();
    }

    public interface Creator<K, V> {
        V create(K key);
    }
}
