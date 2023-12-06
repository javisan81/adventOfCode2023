package day6;

import day6.Race;
import day6.Races;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RaceTest {
    @Test
    void noDistanceNoTime(){
        Races race = new Races(List.of(new Race(0, new BigDecimal(0))));
        assertEquals(0, race.numberOfOptionsToWin());
    }

    @Test
    void oneGame(){
        Races race = new Races(List.of(new Race(7, new BigDecimal(9))));
        assertEquals(4, race.numberOfOptionsToWin());
    }

    @Test
    void twoGames(){
        Races race = new Races(List.of(new Race(7, new BigDecimal(9)), new Race(15, new BigDecimal(40))));
        assertEquals(32, race.numberOfOptionsToWin());
    }

    @Test
    void threeGames(){
        Races race = new Races(List.of(new Race(7, new BigDecimal(9)), new Race(15, new BigDecimal(40)), new Race(30, new BigDecimal(200))));
        assertEquals(288, race.numberOfOptionsToWin());
    }

    @Test
    void finalGame(){
        Races race = new Races(List.of(
                new Race(71530, new BigDecimal(940200))
        ));
        assertEquals(71503, race.numberOfOptionsToWin());
    }

    @Test
    void secondFinalGame(){
        Races race = new Races(List.of(
                new Race(40828492, new BigDecimal(233101111101487L))
        ));
        assertEquals(27102791, race.numberOfOptionsToWin());
    }

}
