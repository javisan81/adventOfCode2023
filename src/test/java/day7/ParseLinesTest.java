package day7;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ParseLinesTest {
    @Test
    void oneLine(){
        ParseLines parseLines= new ParseLines(List.of("TQA26 14"));
        assertEquals(new Game(List.of(new Hand("TQA26", 14))), parseLines.createGame());
    }
}