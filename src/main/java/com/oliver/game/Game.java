package com.oliver.game;

import com.oliver.shuffle.FisherYatesShuffle;
import com.oliver.shuffle.ShufflingAlgorithm;
import com.oliver.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private final Player player1;
    private final Player player2;

    public Game(ShufflingAlgorithm<Integer> shufflingAlgorithm) {
        Deck deck = new Deck(shufflingAlgorithm);
        deck.shuffle(shufflingAlgorithm);
        Pair<Integer[], Integer[]> hands = deck.getHands();
        this.player1 = new Player(hands.getFirst());
        this.player2 = new Player(hands.getSecond());
    }

    public Game(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
    }

    public Game() {
        this(new FisherYatesShuffle());
    }

    public void play() {
        Integer card1;
        Integer card2;
        List<Integer> cardBuffer = new ArrayList<>();

        while(player1.getDrawPileSize() > 0 && player2.getDrawPileSize() > 0) {
            card1 = player1.drawCard();
            card2 = player2.drawCard();
            System.out.printf("Player 1: (%d cards): %d%n", player1.getDrawPileSize(), card1);
            System.out.printf("Player 2: (%d cards): %d%n", player2.getDrawPileSize(), card2);
            cardBuffer = playTurn(card1, card2, cardBuffer);
        }

        if (player1.getDrawPileSize() + player1.getDiscardPileSize() == 0) {
            System.out.println("Player 2 wins");
        } else {
            System.out.println("Player 1 wins");
        }
    }

    public List<Integer> playTurn(Integer card1, Integer card2, List<Integer> cardBuffer) {
        cardBuffer.add(card1);
        cardBuffer.add(card2);

        if(card1 > card2) {
            player1.discard(cardBuffer);
            System.out.println("Player 1 this round\n");
            return new ArrayList<>();
        } else if (card1 < card2) {
            player2.discard(cardBuffer);
            System.out.println("Player 2 this round\n");
            return new ArrayList<>();
        } else {
            System.out.println("No winner in this round\n");
            return cardBuffer;
        }
    }
}
