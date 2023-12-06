package day4;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Card {

    private List<Integer> winnings;
    private List<Integer> mines;

    public Card(String card) {
        String numbersSection = card.replaceAll("Card .*:", "");
        String[] sections = numbersSection.split("\\|");
        winnings = toNumbers(sections[0]);
        mines = toNumbers(sections[1]);
    }


    private List<Integer> toNumbers(String section) {
        return Stream.of(section.trim().split(" "))
                .filter(Predicate.not(String::isEmpty))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }


    public int calculatePoints() {
        int points = 0;
        boolean isFirstMatchFoundInTheCard=false;
        for (Integer mine : mines) {
            if (winnings.contains(mine)) {
                if (isFirstMatchFoundInTheCard) {
                    points = points * 2;
                } else {
                    points = points + 1;
                    isFirstMatchFoundInTheCard=true;
                }
            }
        }
        return points;
    }

    public  Integer hits() {
        int hits = 0;
        for (Integer mine : mines) {
            if (winnings.contains(mine)) {
                hits++;
            }
        }
        return hits;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return Objects.equals(winnings, card.winnings) && Objects.equals(mines, card.mines);
    }

    @Override
    public int hashCode() {
        return Objects.hash(winnings, mines);
    }
}
