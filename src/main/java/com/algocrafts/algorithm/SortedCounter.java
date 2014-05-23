package com.algocrafts.algorithm;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.google.common.collect.TreeMultimap;

import java.util.Collection;
import java.util.Comparator;
import java.util.Map;

import static com.google.common.collect.Maps.newHashMap;

public class SortedCounter<T> {
    private final Map<T, Integer> counter = newHashMap();
    private final Multimap<Integer, T> sortedCounter;

    public SortedCounter() {
        sortedCounter = HashMultimap.create();
    }

    public SortedCounter(Comparator<T> secondComparator) {
        sortedCounter = TreeMultimap.create(
            (count1, count2) -> count2.compareTo(count1),
            secondComparator);
    }

    public Collection<T> collect() {
        return sortedCounter.values();
    }

    public void count(T key) {
        Integer count = counter.get(key);
        sortedCounter.remove(count, key);

        count = count == null ? 1 : count + 1;
        counter.put(key, count);
        sortedCounter.put(count, key);
    }

    /**
     * Remove the first key and return it.
     * @return
     * @throws java.util.NoSuchElementException if there is no value present
     */
    public T removeFirst() {
        int firstCount = getFirst(sortedCounter.keySet());
        T first = getFirst(sortedCounter.get(firstCount));
        counter.remove(first);
        return first;
    }

    private <E> E getFirst(Collection<E> collection) {
        return collection.stream().findFirst().get();
    }

    public boolean containsKey(T key) {
        return counter.containsKey(key);
    }
}