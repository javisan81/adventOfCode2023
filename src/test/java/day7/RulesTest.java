package day7;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RulesTest {
    @Test
    void onePair() {
        assertTrue(new TwoGroupsRepeated(2,1, 1).accept("32T3K"));
    }

    @Test
    void twoPair() {
        assertTrue(new TwoGroupsRepeated(2,2, 1).accept("23432"));
    }

    @Test
    void threeOfAKind() {
        assertTrue(new TwoGroupsRepeated(3, 1, 1).accept("TTT98"));
    }

    @Test
    void fullHouse() {
        assertTrue(new TwoGroupsRepeated(3, 2, 1).accept("23332"));
    }

    @Test
    void fiveOfAKind() {
        assertTrue(new StrengthCalculatorByRepetition(5, 1).accept("AAAAA"));
    }

    @Test
    void fourOfAKind() {
        assertTrue(new StrengthCalculatorByRepetition(4, 1).accept("AA8AA"));
    }

    @Test
    void firstIsStronger() {
        Hand hand1 = new Hand("33332", 1);
        Hand hand2 = new Hand("2AAAA", 1);
        assertEquals(1, hand1.compareTo(hand2));
    }

    @Test
    void firstIsStronger2() {
        Hand hand1 = new Hand("77888", 1);
        Hand hand2 = new Hand("77788", 1);
        assertEquals(1, hand1.compareTo(hand2));
    }

    @Test
    void jsAreWeaker() {
        Hand hand1 = new Hand("KKKKJ", 1);
        Hand hand2 = new Hand("KKKK2", 1);
        assertEquals(1, hand1.compareTo(hand2));
    }

    @Test
    void withOneJ() {
        assertTrue(new StrengthCalculatorByRepetition(3, 1).accept("JK3JT"));
    }

    @Test
    void with5Js() {
        assertTrue(new StrengthCalculatorByRepetition(5, 1).accept("JJJJJ"));
    }

    @Test
    void with3Js() {
        assertTrue(new StrengthCalculatorByRepetition(5, 1).accept("JJJTT"));
    }

    @Test
    void with4Js() {
        assertTrue(new StrengthCalculatorByRepetition(5, 1).accept("J8JJJ"));
    }

    @Test
    void with2Js(){
        assertTrue(new StrengthCalculatorByRepetition(3, 1).accept("JJ38T"));
        assertTrue(new StrengthCalculatorByRepetition(4, 1).accept("99TJJ"));
        assertTrue(new StrengthCalculatorByRepetition(4, 1).accept("646JJ"));
    }

    @Test
    void fullHouseWithJs(){
        assertTrue(new TwoGroupsRepeated(3, 2, 1).accept("99J88"));
    }

}