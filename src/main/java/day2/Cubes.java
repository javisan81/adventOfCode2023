package day2;

import java.util.List;

public class Cubes {
    private final int redCubes;
    private final int greenCubes;
    private final int blueCubes;

    public Cubes(int redCubes, int greenCubes, int blueCubes) {
        this.redCubes = redCubes;
        this.greenCubes = greenCubes;
        this.blueCubes = blueCubes;
    }

    public int sumPossibleIds(List<String> games) {
        return games.stream()
                .filter(this::isPossible)
                .mapToInt(this::id).sum();
    }

    private boolean isPossible(String game) {
        int reds = max("red", game);
        int greens = max("green", game);
        int blues = max("blue", game);
        return reds <= redCubes && greens <= greenCubes && blues <= blueCubes;
    }

    private int max(String color, String game) {
        String[] gameTries = getGameTries(game);
        int result = 1;
        for (String gameTry : gameTries) {
            String[] hand = getHands(gameTry);
            for (String colorCountFound : hand) {
                if (colorCountFound.contains(color)) {
                    result = Math.max(result, Integer.parseInt(colorCountFound.replace(color, "").trim()));
                }
            }
        }
        return result;
    }

    private static String[] getHands(String gameTry) {
        return gameTry.split(",");
    }

    private static String[] getGameTries(String game) {
        return game.replaceAll(".*:", "").split(";");
    }

    private int id(String game) {
        String prefix = game.replaceFirst("Game ", "");
        return Integer.parseInt(prefix.substring(0, prefix.indexOf(":")));
    }

    public int power(List<String> games) {
        return games.stream()
                .mapToInt(this::power)
                .sum();
    }

    private int power(String game) {
        int reds = max("red", game);
        int greens = max("green", game);
        int blues = max("blue", game);
        return reds*greens*blues;
    }
}
