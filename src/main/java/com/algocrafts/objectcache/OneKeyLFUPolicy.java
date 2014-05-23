package com.algocrafts.objectcache;


import com.algocrafts.algorithm.SortedCounter;

public class OneKeyLFUPolicy<K> implements OneKeyCachePolicy<K> {
    private final SortedCounter<K> sortedCounter = new SortedCounter<>();

    @Override
    public K evictingKey(boolean cacheIsFull, K key) {
        K LFU = null;
        if (cacheIsFull && !sortedCounter.containsKey(key)) {
            LFU = sortedCounter.removeFirst();
        }
        sortedCounter.count(key);
        return LFU;
    }
}
