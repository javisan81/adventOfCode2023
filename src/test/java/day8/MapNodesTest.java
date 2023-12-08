package day8;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MapNodesTest {
    @Test
    void noSteps() {
        MapNodes map = new MapNodes(
                List.of(new Node("ZZZ", "ZZZ", "BBB"))
        );
        assertEquals(0, map.statesToAchieveZZZ(""));
    }

    @Test
    void oneStep() {
        MapNodes map = new MapNodes(
                List.of(
                        new Node("AAA", "ZZZ", "BBB"),
                        new Node("ZZZ", "ZZZ", "ZZZ")
                )
        );
        assertEquals(1, map.statesToAchieveZZZ("L"));
    }

    @Test
    void smallExample1() {
        MapNodes map = new MapNodes(
                List.of(
                        new Node("AAA", "BBB", "CCC"),
                        new Node("BBB", "DDD", "EEE"),
                        new Node("CCC", "ZZZ", "GGG"),
                        new Node("DDD", "DDD", "DDD"),
                        new Node("EEE", "EEE", "EEE"),
                        new Node("GGG", "GGG", "GGG"),
                        new Node("ZZZ", "ZZZ", "ZZZ")
                )
        );
        assertEquals(2, map.statesToAchieveZZZ("RL"));
    }

    @Test
    void smallExample2() {
        MapNodes map = new MapNodes(
                List.of(
                        new Node("AAA", "BBB", "BBB"),
                        new Node("BBB", "AAA", "ZZZ"),
                        new Node("ZZZ", "ZZZ", "ZZZ")
                )
        );
        assertEquals(6, map.statesToAchieveZZZ("LLR"));
    }

    @Test
    void firstBigTest() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("hauntedWasteland.txt").toURI()));
        MapNodeParser mapNodeParser = new MapNodeParser(lines);
        MapNodes map = mapNodeParser.parse();
        assertEquals(14257, map.statesToAchieveZZZ(mapNodeParser.getInstructions()));
    }

    @Test
    void secondPartSmallExample() {
        MapNodes map = new MapNodes(
                List.of(
                        new Node("11A", "11B", "XXX"),
                        new Node("11B", "XXX", "11Z"),
                        new Node("11Z", "11B", "XXX"),
                        new Node("22A", "22B", "XXX"),
                        new Node("22B", "22C", "22C"),
                        new Node("22C", "22Z", "22Z"),
                        new Node("22Z", "22B", "22B"),
                        new Node("XXX", "XXX", "XXX")
                )
        );
        assertEquals(6, map.ghostMovesToZs("LR"));
    }

    @Test
    void secondBigTest() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("hauntedWasteland.txt").toURI()));
        MapNodeParser mapNodeParser = new MapNodeParser(lines);
        MapNodes map = mapNodeParser.parse();
        assertEquals(16187743689077L, map.ghostMovesToZs(mapNodeParser.getInstructions()));
    }
}
