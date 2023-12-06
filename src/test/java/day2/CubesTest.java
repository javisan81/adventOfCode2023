package day2;

import day2.Cubes;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CubesTest {
    @Test
    void emptyCubes(){
        var games = new Cubes(12, 13, 14);
        assertEquals(1, games.sumPossibleIds(List.of("Game 1: ")) );
    }

    @Test
    void threeBlue14Red(){
        var games = new Cubes(12, 13, 14);
        assertEquals(0, games.sumPossibleIds(List.of("Game 1: 3 blue, 14 red")) );
    }

    @Test
    void multipleGames() {
        var games = new Cubes(12, 13, 14);
        assertEquals(8, games.sumPossibleIds(
                List.of(
                        "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
                        "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                        "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                        "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
                        "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
                )
        )
        );
    }

    @Test
    void cubesFinal() throws IOException, URISyntaxException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("cubes.txt").toURI()));

        var games = new Cubes(12, 13, 14);

        assertEquals(1931, games.sumPossibleIds(lines));
    }
    @Test
    void cubesPower(){
        var games = new Cubes(12, 13, 14);
        assertEquals(2286, games.power(
                        List.of(
                                "Game 1: 3 blue, 4 red; 1 red, 2 green, 6 blue; 2 green",
                                "Game 2: 1 blue, 2 green; 3 green, 4 blue, 1 red; 1 green, 1 blue",
                                "Game 3: 8 green, 6 blue, 20 red; 5 blue, 4 red, 13 green; 5 green, 1 red",
                                "Game 4: 1 green, 3 red, 6 blue; 3 green, 6 red; 3 green, 15 blue, 14 red",
                                "Game 5: 6 red, 1 blue, 3 green; 2 blue, 1 red, 2 green"
                        )
                )
        );
    }

    @Test
    void cubesPowerFinal() throws IOException, URISyntaxException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("cubes.txt").toURI()));

        var games = new Cubes(12, 13, 14);

        assertEquals(83105, games.power(lines));
    }
}
