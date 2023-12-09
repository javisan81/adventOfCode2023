package day9;

import java.util.*;
import java.util.function.IntBinaryOperator;

public class Oasis {
    private final List<List<Integer>> history;

    public Oasis(List<List<Integer>> history) {
        this.history = history;
    }

    public int extrapolateNewValues() {
        return history.stream()
                .mapToInt(oneLine -> extrapolateElement(extrapolate(oneLine, Predict.NEXT), Integer::sum))
                .sum();
    }

    public int extrapolateFirstValues() {
        return history.stream()
                .mapToInt(oneLine -> extrapolateElement(extrapolate(oneLine, Predict.PREVIOUS), Oasis::reverseSubtract))
                .sum();
    }

    private List<Integer> extrapolate(List<Integer> oneLine, Predict predict) {
        List<Integer> partialResults = new ArrayList<>();
        List<Integer> currentSubtraction = oneLine;
        partialResults.add(partialResult(oneLine, predict));
        while (!areAllZeros(currentSubtraction)) {
            currentSubtraction = getSubtractions(currentSubtraction);
            partialResults.add(partialResult(currentSubtraction, predict));
        }
        return partialResults;
    }

    private int extrapolateElement(List<Integer> partialResults, IntBinaryOperator reducer) {
        List<Integer> reversePartialResults = new ArrayList<>(partialResults);
        Collections.reverse(reversePartialResults);
        return reversePartialResults.stream().mapToInt(Integer::intValue).reduce(0, reducer);
    }

    private static int reverseSubtract(int a, int b) {
        return b - a;
    }

    private static Integer partialResult(List<Integer> oneLine, Predict predict) {
        return switch (predict) {
            case NEXT -> oneLine.get(oneLine.size() - 1);
            case PREVIOUS -> oneLine.get(0);
        };
    }

    private static List<Integer> getSubtractions(List<Integer> oneLine) {
        int lastValue = Integer.MIN_VALUE;
        List<Integer> subtractions = new ArrayList<>();
        for (Integer next : oneLine) {
            if (lastValue != Integer.MIN_VALUE) {
                subtractions.add(next - lastValue);
            }
            lastValue = next;
        }
        return subtractions;
    }

    private boolean areAllZeros(List<Integer> subtractions) {
        return subtractions.stream().allMatch(x -> x == 0);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Oasis oasis = (Oasis) o;
        return Objects.equals(history, oasis.history);
    }

    @Override
    public int hashCode() {
        return Objects.hash(history);
    }

    @Override
    public String toString() {
        return "Oasis{" +
                "history=" + history +
                '}';
    }


}
