package day11;

public record Pair(Galaxy galaxyA, Galaxy galaxyB) {
    public long calculateMinimumDistance() {
        long rows= Math.abs(galaxyA.expandedRow()-galaxyB.expandedRow());
        long cols= Math.abs(galaxyA.expandedCol()-galaxyB.expandedCol());
        return rows+cols;
    }

}
