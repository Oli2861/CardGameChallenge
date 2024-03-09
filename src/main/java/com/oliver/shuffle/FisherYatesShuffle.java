package com.oliver.shuffle;

import java.util.concurrent.ThreadLocalRandom;

public class FisherYatesShuffle implements ShufflingAlgorithm<Integer> {
    private final RandomIntSupplier randomIntSupplier;

    public FisherYatesShuffle(RandomIntSupplier randomIntSupplier) {
        this.randomIntSupplier = randomIntSupplier;
    }

    public FisherYatesShuffle() {
        this.randomIntSupplier = ThreadLocalRandom.current()::nextInt;
    }

    /**
     * Fisher-Yates shuffle algorithm
     *
     * Wikipedia:
     * To initialize an array a of n elements to a randomly shuffled copy of source, both 0-based:
     * for i from 0 to n − 1 do
     * j <- random integer such that 0 ≤ j ≤ i
     * if j ≠ i
     * a[i] <- a[j]
     * a[j] <- source[i]
     *
     * @param arr The list to shuffle.
     * @return The shuffled list.
     */
    public Integer[] shuffle(Integer[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int j = i == 0 ? 0 : randomIntSupplier.get(i);
            if (j != i) {
                // Swap arr[i] and arr[j]
                int tmp = arr[i];
                arr[i] = arr[j];
                arr[j] = tmp;
            }
        }
        return arr;
    }

}
