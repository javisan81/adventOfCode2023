package day3;

import day3.Engine;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EngineTest {
    @Test
    void empty() {
        char[][] schematic = List.of("467..114..".toCharArray()).toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(0, engine.calculateSumSchematics());
    }

    @Test
    void fourSixSeven() {
        char[][] schematic = List.of("467..114..".toCharArray(), "...*......".toCharArray()).toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(467, engine.calculateSumSchematics());
    }


    @Test
    void fourFiveAtTheEnd() {
        char[][] schematic = List.of("*45".toCharArray()).toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(45, engine.calculateSumSchematics());
    }

    @Test
    void fourFiveStarting() {
        char[][] schematic = List.of("45*".toCharArray()).toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(45, engine.calculateSumSchematics());
    }

    @Test
    void minusFive() {
        char[][] schematic = List.of("-5".toCharArray()).toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(5, engine.calculateSumSchematics());
    }
    @Test
    void largLine() {
        char[][] schematic = List.of(
                "............................989....*.....954.....69.....488../.......90.495*..........697....*.......*.....*..686.......=.......594*981.....".toCharArray(),
                ".....104..#...................=.....773...$../.....*396.....959.............272...........433........889.821.....*759.469...................".toCharArray()
                ).
                toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(10539, engine.calculateSumSchematics());
    }

    @Test
    void anoterExample() {
        char[][] schematic = List.of(
                        "218*...".toCharArray(),
                        "....395".toCharArray()
                ).
                toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(613, engine.calculateSumSchematics());
    }



    @Test
    void smallExample() {
        char[][] schematic = List.of(
                        "467..114..".toCharArray(),
                        "...*......".toCharArray(),
                        "..35..633.".toCharArray(),
                        "......#...".toCharArray(),
                        "617*......".toCharArray(),
                        ".....+.58.".toCharArray(),
                        "..592.....".toCharArray(),
                        "......755.".toCharArray(),
                        "...$.*....".toCharArray(),
                        ".664.598..".toCharArray()
                )
                .toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(4361, engine.calculateSumSchematics());
    }
    @Test
    void test4(){
        char[][] schematic = List.of(
                        "...12".toCharArray(),
                        "12*..".toCharArray()
                ).
                toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(24, engine.calculateSumSchematics());
    }

    @Test
    void test10(){
        char[][] schematic = List.of(
                        "...127".toCharArray(),
                        "....+.".toCharArray()
                ).
                toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(127, engine.calculateSumSchematics());
    }

    @Test
    void test5(){
        char[][] schematic = List.of(
                        "-12*..".toCharArray()
                ).
                toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(12, engine.calculateSumSchematics());
    }

    @Test
    void test6(){
        char[][] schematic = List.of(
                        "-12*..".toCharArray()
                ).
                toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(12, engine.calculateSumSchematics());
    }

    @Test
    void test7(){
        char[][] schematic = List.of(
                        "...".toCharArray()
                ).
                toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(0, engine.calculateSumSchematics());
    }

    @Test
    void test8(){
        char[][] schematic = List.of(
                        "12+12".toCharArray()
                ).
                toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(24, engine.calculateSumSchematics());
    }
    @Test
    void ten11(){
        char[][] schematic = List.of(
                        "12.......*..".toCharArray(),
                        "+.........34".toCharArray(),
                        ".......-12..".toCharArray(),
                        "..78........".toCharArray(),
                        "..*....60...".toCharArray(),
                        "78.........9".toCharArray(),
                        ".5.....23..$".toCharArray(),
                        "8...90*12...".toCharArray(),
                        "............".toCharArray(),
                        "2.2......12.".toCharArray(),
                        ".*.........*".toCharArray(),
                        "1.1..503+.56".toCharArray()
                )
                .toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(925, engine.calculateSumSchematics());
    }
    @Test
    void ten12(){
        char[][] schematic = List.of(
                        "....................".toCharArray(),
                        "..-52..52-..52..52..".toCharArray(),
                        "..................-.".toCharArray()
                )
                .toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(156, engine.calculateSumSchematics());
    }

    @Test
    void
    engineFinal() throws IOException, URISyntaxException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("engine.txt").toURI()));
        System.out.println(lines.get(0));
        var engine = new Engine(lines.stream().map(String::toCharArray).toList().toArray(new char[0][0]));

        assertEquals(535078, engine.calculateSumSchematics());
    }


    @Test
    void gearsSmallExample() {
        char[][] schematic = List.of(
                        "467..114..".toCharArray(),
                        "...*......".toCharArray(),
                        "..35..633.".toCharArray()
                )
                .toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(16345, engine.calculateGears());
    }

    @Test
    void gearsSmallExample2() {
        char[][] schematic = List.of(
                "617*......".toCharArray()
                )
                .toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(0, engine.calculateGears());
    }

    @Test
    void gearsSmallExample3() {
        char[][] schematic = List.of(
                        "467..114..".toCharArray(),
                        "...*......".toCharArray(),
                        "..35..633.".toCharArray(),
                        "......#...".toCharArray(),
                        "617*......".toCharArray(),
                        ".....+.58.".toCharArray(),
                        "..592.....".toCharArray(),
                        "......755.".toCharArray(),
                        "...$.*....".toCharArray(),
                        ".664.598..".toCharArray()
                )
                .toArray(new char[0][0]);
        Engine engine = new Engine(schematic);
        assertEquals(467835, engine.calculateGears());
    }

    @Test
    void gearsFinal() throws IOException, URISyntaxException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("engine.txt").toURI()));
        var engine = new Engine(lines.stream().map(String::toCharArray).toList().toArray(new char[0][0]));

        assertEquals(75312571, engine.calculateGears());
    }
}
