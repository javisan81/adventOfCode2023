package day11;

import java.util.Set;

public record Galaxy(int galaxyName, int row, int col, Set<Integer> emptyRows,
                     Set<Integer> emptyCols, int expansion) implements Comparable<Galaxy> {
    @Override
    public int compareTo(Galaxy other) {
        return galaxyName - other.galaxyName;
    }

    public long expandedRow() {
        return expand(row, emptyRows);
    }

    public long expandedCol() {
        return expand(col, emptyCols);
    }

    private long expand(int position, Set<Integer> emptyPositions) {
        long emptyPositionsExpandedBefore = emptyPositions.stream().filter(emptyPosition -> position > emptyPosition).count();
        return position + (emptyPositionsExpandedBefore * (expansion - 1));
    }
}
