package day7;

public class TwoGroupsRepeated {
    private final int strength;
    private final int othersRepeated;
    private final int mainGroupRepeated;

    public TwoGroupsRepeated(int mainGroupRepeated, int othersRepeated, int strength) {
        this.mainGroupRepeated = mainGroupRepeated;
        this.strength = strength;
        this.othersRepeated = othersRepeated;
    }

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

    public Integer strength() {
        return strength;
    }

    private static Repetition getMaxRepetitions(String hand) {
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
