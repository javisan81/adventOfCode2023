package day7;

import static day7.StrengthCalculator.getMaxRepetitions;

public class TwoCardsRepeated implements StrengthCalculator {
    private final int strength;
    private final int othersRepeated;

    public TwoCardsRepeated(int othersRepeated, int strength) {
        this.strength = strength;
        this.othersRepeated = othersRepeated;
    }

    @Override
    public boolean accept(String hand) {
        Repetition maxRepetitions = getMaxRepetitions(hand);
        return maxRepetitions.repetitions() == 2 &&
                getMaxRepetitions(
                        removeCharacter(hand, maxRepetitions.character())
                ).repetitions() == othersRepeated;
    }

    private static String removeCharacter(String hand, char character) {
        return hand.replaceAll(Character.toString(character), "").replaceAll("J", "");
    }

    @Override
    public Integer strength() {
        return strength;
    }
}
