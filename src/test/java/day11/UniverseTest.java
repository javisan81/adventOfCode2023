package day11;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UniverseTest {
    @Test
    void zeroSum() {
        Universe universe = new Universe(
                2, new char[0][0]
        );
        assertEquals(0, universe.sumOfDistances());
    }

    @Test
    void smallExample() {
        String universeRaw= """
                ...#......
                .......#..
                #.........
                ..........
                ......#...
                .#........
                .........#
                ..........
                .......#..
                #...#.....
                """;
        Universe universe = new Universe(
                2, toArray(universeRaw)
        );
        assertEquals(374, universe.sumOfDistances());
    }

    @Test
    void bigExample() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("universe.txt").toURI()));
        Universe universe = new Universe(
                2,
                toChars(lines.toArray(new String[0]))
        );
        assertEquals(10231178, universe.sumOfDistances());
    }

    @Test
    void smallExamplePart2() throws URISyntaxException, IOException {
        String universeRaw= """
                ...#......
                .......#..
                #.........
                ..........
                ......#...
                .#........
                .........#
                ..........
                .......#..
                #...#.....
                """;
        Universe universe = new Universe(
                10, toArray(universeRaw)
        );
        assertEquals(1030, universe.sumOfDistances());
    }

    @Test
    void bigExamplePart2() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("universe.txt").toURI()));
        Universe universe = new Universe(
                1000000,
                toChars(lines.toArray(new String[0]))
        );
        assertEquals(622120986954L, universe.sumOfDistances());
    }

    private char[][] toArray(String universeRaw) {
        return toChars(universeRaw.split("\n"));
    }

    private static char[][] toChars(String[] rows) {
        int maxDimensions= rows.length;
        char[][] result =new char[maxDimensions][maxDimensions];
        for(int i=0;i<maxDimensions;i++){
            result[i] = rows[i].toCharArray();
        }
        return result;
    }
}
