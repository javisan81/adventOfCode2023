package day4;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardsTest {
    @Test
    void oneCard(){
        Cards cards = new Cards(List.of("Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53"));
        assertEquals(8, cards.calculatePoints());
    }

    @Test
    void multipleCards(){
        Cards cards = new Cards(
                List.of(
                        "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                        "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
                        "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
                        "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
                        "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
                        "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
                )
        );
        assertEquals(13, cards.calculatePoints());
    }
    @Test
    void oneCard2(){
        Cards cards = new Cards(List.of("Card   1: 61 73 92 28 96 76 32 62 44 53 | 61 17 26 13 92  5 73 29 53 42 62 46 96 32 21 97 99 28 12  4  7 44 19 71 76"));
        assertEquals(512, cards.calculatePoints());
    }

    @Test
    void cardsFinal() throws IOException, URISyntaxException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("cards.txt").toURI()));
        var cards = new Cards(lines);
        assertEquals(24706, cards.calculatePoints());
    }

    @Test
    void cardsCopiesFinalSmall() throws IOException, URISyntaxException {
        Cards cards = new Cards(
                List.of(
                        "Card 1: 41 48 83 86 17 | 83 86  6 31 17  9 48 53",
                        "Card 2: 13 32 20 16 61 | 61 30 68 82 17 32 24 19",
                        "Card 3:  1 21 53 59 44 | 69 82 63 72 16 21 14  1",
                        "Card 4: 41 92 73 84 69 | 59 84 76 51 58  5 54 83",
                        "Card 5: 87 83 26 28 32 | 88 30 70 12 93 22 82 36",
                        "Card 6: 31 18 13 56 72 | 74 77 10 23 35 67 36 11"
                )
        );
        assertEquals(30, cards.totalScratchCards());
    }

    @Test
    void cardsFinal2() throws IOException, URISyntaxException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("cards.txt").toURI()));
        var cards = new Cards(lines);
        assertEquals(13114317, cards.totalScratchCards());
    }

}
