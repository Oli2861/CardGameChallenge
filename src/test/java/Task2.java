import com.oliver.game.Player;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task2 {

    @Test
    public void testDrawCard() {
        Integer[] hand = new Integer[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        Player subject = new Player(hand);

        for (int i = 0; i < hand.length; i++) {
            Integer card = subject.drawCard();
            int remainingCards = subject.getDrawPileSize();
            assertEquals(hand.length - i - 1, remainingCards);
            assertEquals(hand[hand.length - i - 1], card);

            // Shuffle drawn cards into the discard pile.
            subject.discard(card);
        }
        // Corresponds to the size of the hand, as we directly shuffled drawn cards into the discard pile.
        assertEquals(hand.length, subject.getDiscardPileSize());
        assertEquals(0, subject.getDrawPileSize());

        // Will shuffle the discard pile and add it to the draw pile, as the size of the draw pile is 0 (previous assertion).
        Integer card = subject.drawCard();
        // All cards from the discard pile are now in the draw pile. This corresponds to the size of the hand, as we directly shuffled drawn cards into the discard pile.
        assertEquals(hand.length - 1, subject.getDrawPileSize());
    }
}
