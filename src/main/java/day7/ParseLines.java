package day7;

import java.util.List;
import java.util.stream.Collectors;

public class ParseLines {
    private final List<String> lines;

    public ParseLines(List<String> lines) {
        this.lines = lines;
    }

    public Game createGame() {
        List<Hand> hands = lines.stream()
                .map(line -> new Hand(getHand(line), getBid(line)))
                .toList();
        return new Game(hands);
    }

    private int getBid(String line) {
        return Integer.parseInt(line.split(" ")[1]);
    }

    private String getHand(String line) {
        return line.split(" ")[0];
    }
}
