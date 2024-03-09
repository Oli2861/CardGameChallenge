package com.oliver.game;

import com.oliver.shuffle.ShufflingAlgorithm;
import com.oliver.util.Pair;

public class Deck {
    private Integer[] cards;

    public Deck(
            ShufflingAlgorithm<Integer> shufflingAlgorithm,
            int deckSize,
            int cardUpperLimit,
            int timesIncluded
    ) {
        if(cardUpperLimit * timesIncluded != deckSize) {
            throw new IllegalArgumentException("Deck size must be a multiple of the number of cards and the number of times each card is included in the deck.");
        }
        this.cards = new Integer[deckSize];

        for(int i = 0; i < cardUpperLimit; i++) {
            for(int j = 0; j < timesIncluded; j++) {
                this.cards[i * timesIncluded + j] = i;
            }
        }
    }

    public Deck(ShufflingAlgorithm<Integer> shufflingAlgorithm) {
        this(shufflingAlgorithm, 40, 10, 4);
    }

    public Integer[] getCards() {
        return this.cards;
    }

    public void shuffle(ShufflingAlgorithm<Integer> shufflingAlgorithm) {
        this.cards = shufflingAlgorithm.shuffle(this.cards);
    }

    /**
     * Splits the deck into two hands.
     * The hands are always of equal size.
     * If the deck has an odd number of cards, the last card will be left in the deck.
     * @return A pair of hands.
     */
    public Pair<Integer[], Integer[]> getHands() {
        int half = this.cards.length / 2;
        Integer[] hand1 = new Integer[half];
        Integer[] hand2 = new Integer[half];

        for(int i = 0; i < half; i++) {
            hand1[i] = this.cards[i];
            hand2[i] = this.cards[i + half];
        }

        return new Pair<>(hand1, hand2);
    }
}
