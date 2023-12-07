package day7;

import java.util.List;

public interface StrengthCalculator {
    boolean accept(String hand);

    Integer strength();

    static List<StrengthCalculator> rules() {
        return List.of(
                new StrengthCalculatorByRepetition(5, 7),
                new StrengthCalculatorByRepetition(4, 6),
                new TwoGroupsRepeated(3, 2, 5),
                new TwoGroupsRepeated(3, 1, 4),
                new TwoGroupsRepeated(2, 2, 3),
                new TwoGroupsRepeated(2, 1, 1),
                new StrengthCalculatorByRepetition(0, 0)
        );
    }

    static Repetition getMaxRepetitions(String hand) {
        Repetition maxRepetition = new Repetition('J', 0);
        String handWithoutJs = hand.replaceAll("J", "");
        for (int i = 0; i < handWithoutJs.length(); i++) {
            char character = handWithoutJs.charAt(i);
            int repetitions = getRepetitions(handWithoutJs.substring(i + 1), character);
            if (repetitions > maxRepetition.repetitions()) {
                maxRepetition = new Repetition(character, repetitions);
            }
        }
        return new Repetition(maxRepetition.character(), maxRepetition.repetitions() + countJs(hand));
    }

    private static int countJs(String hand) {
        return (int) hand.chars().mapToObj(c -> (char) c).filter(c -> c == 'J').count();
    }

    private static int getRepetitions(String hand, char character) {
        int repetitions = 1;
        for (int j = 0; j < hand.length(); j++) {
            char character2 = hand.charAt(j);
            if (character == character2 || character == 'J' || character2 == 'J') {
                repetitions++;
            }
        }
        return repetitions;
    }
}
