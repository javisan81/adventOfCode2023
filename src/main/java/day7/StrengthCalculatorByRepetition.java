package day7;

public class StrengthCalculatorByRepetition implements StrengthCalculator {

    private final int numberOfCardsRepeated;
    private final int strength;

    StrengthCalculatorByRepetition(int numberOfCardsRepeated, int strength) {
        this.numberOfCardsRepeated = numberOfCardsRepeated;
        this.strength = strength;
    }

    @Override
    public boolean accept(String hand) {
        Repetition maxRepetition = StrengthCalculator.getMaxRepetitions(hand);
        return maxRepetition.repetitions() == numberOfCardsRepeated;
    }


    @Override
    public Integer strength() {
        return strength;
    }


}
