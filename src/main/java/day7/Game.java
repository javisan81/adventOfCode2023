package day7;

import java.util.List;
import java.util.Objects;

public class Game {
    private final List<Hand> hands;

    public Game(List<Hand> hands) {
        this.hands = hands;
    }

    public int totalWinnings() {
        int result = 0;
        List<Hand> handsSorted = hands.stream().sorted().toList();
        for (int i = 0; i < handsSorted.size(); i++) {
            result = result + handsSorted.get(i).amount(i + 1);
        }
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Game game = (Game) o;
        return Objects.equals(hands, game.hands);
    }

    @Override
    public int hashCode() {
        return Objects.hash(hands);
    }
}
