package day4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Cards {
    class RepeatCard {
        Card card;
        int repetitions;

        public RepeatCard(Card card, int repetitions) {
            this.card = card;
            this.repetitions = repetitions;
        }

        public void addCopy() {
            repetitions++;
        }
    }

    ;

    private final List<String> cards;

    public Cards(List<String> strings) {
        this.cards = strings;
    }

    public int calculatePoints() {
        return cards.stream()
                .mapToInt(this::calculate)
                .sum();
    }

    private int calculate(String s) {
        Card card = new Card(s);
        int points = card.calculatePoints();
        return points;
    }

    public int totalScratchCards() {
        Map<Integer, RepeatCard> totalHitsByCard = new HashMap<>();
        for (int i = 0; i < cards.size(); i++) {
            totalHitsByCard.put(i, new RepeatCard(new Card(cards.get(i)), 1));
        }
        for (int i = 0; i < cards.size(); i++) {
            RepeatCard cardsFound = totalHitsByCard.get(i);
            Integer copies = cardsFound.card.hits();
            for (int x = 0; x < cardsFound.repetitions; x++) {
                for (int j = i + 1; j < Math.min(i + copies + 1, cards.size()); j++) {
                    RepeatCard nextCards = totalHitsByCard.get(j);
                    nextCards.addCopy();
                }
            }
        }
        return totalHitsByCard.values().stream()
                .mapToInt(c -> c.repetitions)
                .sum();
    }
}
