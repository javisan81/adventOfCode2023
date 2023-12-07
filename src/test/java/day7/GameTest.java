package day7;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GameTest {
    @Test
    void emptyCards(){
        Game game = new Game(
                new ArrayList<Hand>()
        );
        assertEquals(0, game.totalWinnings());
    }

    @Test
    void hand32T3K(){
        Game game = new Game(
                List.of(new Hand("32T3K", 765))
        );
        assertEquals(765, game.totalWinnings());
    }

    @Test
    void twoHands(){
        Game game = new Game(
                List.of(
                        new Hand("32T3K", 765),
                        new Hand("T55J5", 684)
                )
        );
        assertEquals(2133, game.totalWinnings());
    }


    @Test
    void secondPartSmall() {
        Game game = new Game(
                List.of(
                        new Hand("32T3K", 765),
                        new Hand("T55J5", 684),
                        new Hand("KK677", 28),
                        new Hand("KTJJT", 220),
                        new Hand("QQQJA", 483)
                )
        );
        assertEquals(5905, game.totalWinnings());
    }

    @Test
    void test2() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("camelCards.txt").toURI()));
        ParseLines parseLines = new ParseLines(lines);
        assertEquals(253630098, parseLines.createGame().totalWinnings());
    }
}
//>254716747