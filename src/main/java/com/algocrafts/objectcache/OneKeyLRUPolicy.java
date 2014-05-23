package com.algocrafts.objectcache;


import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class OneKeyLRUPolicy<K> implements OneKeyCachePolicy<K> {
    private final Set<K> accessLog = new LinkedHashSet<>();

    @Override
    public K evictingKey(boolean cacheIsFull, K key) {
        K LRU = null;
        if (cacheIsFull && !accessLog.remove(key)) {
            Iterator<K> iterator = accessLog.iterator();
            LRU = iterator.next();
            iterator.remove();
        }
        accessLog.add(key);
        return LRU;
    }
}
