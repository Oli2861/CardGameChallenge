package com.oliver.shuffle;

public interface ShufflingAlgorithm<T> {
    /**
     * Shuffles the list according to some algorithm.
     * @param list The list to shuffle.
     * @return The shuffled list.
     */
    public T[] shuffle(T[] list);

}
