package day6;

import java.util.List;

public class Races {
    private final List<Race> races;

    public Races(List<Race> races) {
        this.races = races;
    }

    public int numberOfOptionsToWin() {
        return races.stream()
                .mapToInt(Race::numberOfOptionsToWin)
                .reduce(1, (a,b) -> a*b);
    }
}
