import com.oliver.game.Game;
import com.oliver.game.Player;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task3 {

    @Test
    public void higherCardWins() {
        Player player1Mock = Mockito.mock(Player.class);
        Player player2Mock = Mockito.mock(Player.class);
        Game subject = new Game(player1Mock, player2Mock);

        List<Integer> cardBuffer = new ArrayList<>();
        List<Integer> actual = subject.playTurn(10, 9, cardBuffer);
        assertEquals(0, actual.size());

        Mockito.verifyZeroInteractions(player2Mock);
        Mockito.verify(player1Mock, Mockito.times(1)).discard(Mockito.anyList());
    }

    @Test
    public void cardsAccumulate() {
        // Arrange: 2 round draw, player 2 finally wins
        Player player1Mock = new Player(new Integer[]{4, 5, 5});
        Player player2Mock = new Player(new Integer[]{5, 5, 5});
        Game subject = new Game(player1Mock, player2Mock);

        // Act
        subject.play();

        // Assert
        assertEquals(0, player1Mock.getDiscardPileSize());
        assertEquals(6, player2Mock.getDiscardPileSize());

    }
}
