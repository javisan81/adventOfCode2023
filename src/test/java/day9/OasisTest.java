package day9;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OasisTest {
    @Test
    void noLines() {
        Oasis oasis = new Oasis(new ArrayList<>());
        assertEquals(0, oasis.extrapolateNewValues());
    }

    @Test
    void oneLine() {
        Oasis oasis = new Oasis(
                List.of(
                        List.of(0, 3, 6, 9, 12, 15)
                )
        );
        assertEquals(18, oasis.extrapolateNewValues());
    }

    @Test
    void twoLines() {
        Oasis oasis = new Oasis(
                List.of(
                        List.of(0, 3, 6, 9, 12, 15),
                        List.of(1, 3, 6, 10, 15, 21)
                )
        );
        assertEquals(46, oasis.extrapolateNewValues());
    }

    @Test
    void negativeNumber() {
        Oasis oasis = new Oasis(
                List.of(
                        List.of(0, -3, -6, -9, -12, -15)
                )
        );
        assertEquals(-18, oasis.extrapolateNewValues());
    }

    @Test
    void smallExample() {
        Oasis oasis = new Oasis(
                List.of(
                        List.of(0, 3, 6, 9, 12, 15),
                        List.of(1, 3, 6, 10, 15, 21),
                        List.of(10, 13, 16, 21, 30, 45)
                )
        );
        assertEquals(114, oasis.extrapolateNewValues());
    }

    @Test
    void firstPart() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("oasis.txt").toURI()));
        Oasis oasis=new OasisParser().parse(lines);
        assertEquals(1939607039, oasis.extrapolateNewValues());
    }

    @Test
    void oneLinePart2() {
        Oasis oasis = new Oasis(
                List.of(
                        List.of(10, 13, 16, 21, 30, 45)
                )
        );
        assertEquals(5, oasis.extrapolateFirstValues());
    }

    @Test
    void smallExamplePart2() {
        Oasis oasis = new Oasis(
                List.of(
                        List.of(0, 3, 6, 9, 12, 15),
                        List.of(1, 3, 6, 10, 15, 21),
                        List.of(10, 13, 16, 21, 30, 45)
                )
        );
        assertEquals(2, oasis.extrapolateFirstValues());
    }

    @Test
    void secondPart() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("oasis.txt").toURI()));
        Oasis oasis=new OasisParser().parse(lines);
        assertEquals(1041, oasis.extrapolateFirstValues());
    }
}
