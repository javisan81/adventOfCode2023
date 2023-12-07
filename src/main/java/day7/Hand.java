package day7;

import java.util.List;
import java.util.Objects;

public class Hand implements Comparable<Hand> {
    private static String orderOfLabels="AKQT98765432J";
    private static final List<TwoGroupsRepeated> strengthCalculators = List.of(
            new TwoGroupsRepeated(5, 0, 7),
            new TwoGroupsRepeated(4, 1, 6),
            new TwoGroupsRepeated(3, 2, 5),
            new TwoGroupsRepeated(3, 1, 4),
            new TwoGroupsRepeated(2, 2, 3),
            new TwoGroupsRepeated(2, 1, 1),
            new TwoGroupsRepeated(0, 0, 0)
    );
    private final String hand;
    private final int bid;

    public Hand(String hand, int bid) {
        this.hand = hand;
        this.bid = bid;
    }

    public int amount(int rank) {
        return bid * rank;
    }

    @Override
    public int compareTo(Hand other) {
        int result = this.strength().compareTo(other.strength());
        int i = 0;
        while (result == 0 && i < this.hand.length()) {
            result = strength(other.hand.charAt(i)) - strength(this.hand.charAt(i));
            i++;
        }
        return result;
    }

    private int strength(char card) {
        return orderOfLabels.indexOf(card);
    }

    public Integer strength() {
        for (var rule : strengthCalculators) {
            if (rule.accept(hand)) {
                return rule.strength();
            }
        }
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hand hand1 = (Hand) o;
        return bid == hand1.bid && Objects.equals(hand, hand1.hand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hand, bid);
    }

    @Override
    public String toString() {
        return "Hand{" +
                "hand='" + hand + '\'' +
                ", bid=" + bid +
                '}';
    }
}
