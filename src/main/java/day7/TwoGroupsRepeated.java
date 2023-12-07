package day7;

import static day7.StrengthCalculator.getMaxRepetitions;

public class TwoGroupsRepeated implements StrengthCalculator {
    private final int strength;
    private final int othersRepeated;
    private final int mainGroupRepeated;

    public TwoGroupsRepeated(int mainGroupRepeated, int othersRepeated, int strength) {
        this.mainGroupRepeated = mainGroupRepeated;
        this.strength = strength;
        this.othersRepeated = othersRepeated;
    }

    @Override
    public boolean accept(String hand) {
        Repetition maxRepetitions = getMaxRepetitions(hand);
        return maxRepetitions.repetitions() == mainGroupRepeated &&
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
