package day10;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static day10.TileType.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PipeMazeTest {
    @Test
    void noLoop() {
        PipeMaze pipeMaze = new PipeMaze(new TileType[][]{
                {
                        START
                }
        }
        );
        assertEquals(0, pipeMaze.farthestPointInLoop());
    }

    @Test
    void simpleLoop() {
        PipeMaze pipeMaze = new PipeMaze(new TileType[][]{
                {
                        START, HORIZONTAL, SEVEN
                },
                {
                        VERTICAL, GROUND, VERTICAL
                },
                {
                        L, HORIZONTAL, J
                }
        }
        );
        assertEquals(4, pipeMaze.farthestPointInLoop());
    }

    @Test
    void firstPartSmallMaze() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("smallMaze.txt").toURI()));
        PipeMaze pipeMaze = new PipeMaze(
                toArrayOfTileType(lines)
        );
        assertEquals(8, pipeMaze.farthestPointInLoop());
    }

    @Test
    void firstPartComplextLoop() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("firstMaze.txt").toURI()));
        PipeMaze pipeMaze = new PipeMaze(
                toArrayOfTileType(lines)
        );
        assertEquals(6757, pipeMaze.farthestPointInLoop());
    }

    @Test
    void secondPArtSimpleExample() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("secondPartSimpleExample.txt").toURI()));
        PipeMaze pipeMaze = new PipeMaze(
                toArrayOfTileType(lines)
        );
        assertEquals(4, pipeMaze.tilesEnclosedInLoop());
    }

    @Test
    void secondPartMediumMaze() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("secondPartMediumMaze.txt").toURI()));
        PipeMaze pipeMaze = new PipeMaze(
                toArrayOfTileType(lines)
        );
        assertEquals(8, pipeMaze.tilesEnclosedInLoop());
    }

    @Test
    void secondPartAnotherdiumBigMaze() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("mediumBigMaze.txt").toURI()));
        PipeMaze pipeMaze = new PipeMaze(
                toArrayOfTileType(lines)
        );
        assertEquals(10, pipeMaze.tilesEnclosedInLoop());
    }

    @Test
    void bigSecondPArt() throws URISyntaxException, IOException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("firstMaze.txt").toURI()));
        PipeMaze pipeMaze = new PipeMaze(
                toArrayOfTileType(lines)
        );
        assertEquals(523, pipeMaze.tilesEnclosedInLoop());
    }

    private TileType[][] toArrayOfTileType(List<String> lines) {
        TileType[][] result = new TileType[lines.size()][lines.get(0).length()];
        int row = 0;
        for (String line : lines) {
            result[row] = toTileTypes(line);
            row++;
        }
        return result;
    }

    private TileType[] toTileTypes(String line) {
        return line.chars()
                .mapToObj(c -> TileType.from((char) c))
                .toArray(TileType[]::new);
    }
}
