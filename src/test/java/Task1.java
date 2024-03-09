import com.oliver.game.Deck;
import com.oliver.shuffle.FisherYatesShuffle;
import com.oliver.shuffle.RandomIntSupplier;
import com.oliver.shuffle.ShufflingAlgorithm;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class Task1 {

    @Test
    public void testDeckSize() {
        Deck deck = new Deck(new FisherYatesShuffle());
        Integer[] cards = deck.getCards();

        assertEquals(cards.length, 40);
    }

    @Test
    public void testShuffle() {
        RandomIntSupplier randomIntSupplierMock = Mockito.mock(RandomIntSupplier.class);
        Integer[] cards = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

        for (int i = 0; i <= cards.length; i++) {
            // Test by reversing the array except for the first element.
            // Therefore, we should not swap after the half of the array,
            // or otherwise the algorithm will restore the original order.
            // Fisher-Yates won't swap if the random number is equal to the current index.
            // Therefore, return the current index as the random number starting from the middle of the array.
            int res = i < cards.length / 2 ? cards.length - i : i;
            Mockito.when(randomIntSupplierMock.get(i)).thenReturn(res);
        }

        ShufflingAlgorithm<Integer> subject = new FisherYatesShuffle(randomIntSupplierMock);
        Integer[] shuffledCards = subject.shuffle(cards.clone());

        // The first element can never be swapped:
        // The random number is chosen as follows: "j <- random integer such that 0 ≤ j ≤ i".
        // As the random number between 0 and 0 is always 0, the first element will never be swapped.
        assertEquals(cards[0], shuffledCards[0]);
        for (int i = 1; i < cards.length; i++) {
            assertEquals(cards[i], shuffledCards[cards.length - i]);
        }
    }


}
