package com.oliver.game;

import com.oliver.shuffle.FisherYatesShuffle;
import com.oliver.shuffle.ShufflingAlgorithm;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Stack;

public class Player {
    private final ShufflingAlgorithm<Integer> shufflingAlgorithm;
    private Stack<Integer> drawPile;
    private List<Integer> discardPile;

    public Player(Integer[] hand, ShufflingAlgorithm<Integer> shufflingAlgorithm) {
        this.discardPile = new ArrayList<>();
        this.shufflingAlgorithm = shufflingAlgorithm;
        setDrawPile(hand);
    }

    public Player(Integer[] hand) {
        this(hand, new FisherYatesShuffle());
    }

    private void setDrawPile(Integer[] drawPile) {
        this.drawPile = new Stack<>();
        for(Integer i : drawPile)
            this.drawPile.push(i);
    }

    public Integer drawCard() {
        if(this.drawPile.isEmpty()) {
            Integer[] arr = new Integer[this.discardPile.size()];
            arr = this.discardPile.toArray(arr);
            Integer[] shuffled = this.shufflingAlgorithm.shuffle(arr);
            this.discardPile = new ArrayList<>();
            setDrawPile(shuffled);
        }
        return this.drawPile.pop();
    }

    public int getDrawPileSize() {
        return this.drawPile.size();
    }

    public int getDiscardPileSize() {
        return this.discardPile.size();
    }

    public void discard(Integer card) {
        this.discardPile.add(card);
    }

    public void discard(Collection<Integer> cards) {
        this.discardPile.addAll(cards);
    }

}
