package day11;

import java.util.*;

public class Universe {
    public static final int NO_GALAXY = 0;
    private final int[][] universe;
    private final int factorOfExpansion;
    private final Set<Integer> emptyRows;
    private final Set<Integer> emptyCols;

    public Universe(int factorOfExpansion, char[][] observedUniverse) {
        this.factorOfExpansion = factorOfExpansion;
        emptyRows = findEmptyRows(observedUniverse);
        emptyCols = findEmptyCols(observedUniverse);
        this.universe = nameGalaxies(observedUniverse);
    }

    private int[][] nameGalaxies(char[][] expandedUniverse) {
        if(expandedUniverse.length==0){
            return new int[0][0];
        }
        int nameOfGalaxy = 1;
        int[][] namedUniverse = new int[expandedUniverse.length][expandedUniverse[0].length];
        for (int i = 0; i < namedUniverse.length; i++) {
            for (int j = 0; j < namedUniverse[0].length; j++) {
                if (expandedUniverse[i][j] == '#') {
                    namedUniverse[i][j] = nameOfGalaxy;
                    nameOfGalaxy++;
                } else {
                    namedUniverse[i][j] = NO_GALAXY;
                }
            }
        }
        return namedUniverse;
    }



    private Set<Integer> findEmptyRows(char[][] observedUniverse) {
        Set<Integer> rows = new TreeSet<>();
        for (int i = 0; i < observedUniverse.length; i++) {
            boolean isEmpty = true;
            for (int j = 0; j < observedUniverse.length; j++) {
                isEmpty = isEmpty && observedUniverse[i][j] == '.';
            }
            if (isEmpty)
                rows.add(i);
        }
        return rows;
    }

    private Set<Integer> findEmptyCols(char[][] observedUniverse) {
        Set<Integer> cols = new HashSet<>();
        for (int i = 0; i < observedUniverse.length; i++) {
            boolean isEmpty = true;
            for (int j = 0; j < observedUniverse.length; j++) {
                isEmpty = isEmpty && observedUniverse[j][i] == '.';
            }
            if (isEmpty)
                cols.add(i);
        }
        return cols;
    }

    public long sumOfDistances() {
        List<Pair> pairsOfGalaxies = getPairs(getGalaxies());
        return pairsOfGalaxies.stream()
                .mapToLong(Pair::calculateMinimumDistance)
                .sum();
    }

    private static List<Pair> getPairs(Set<Galaxy> galaxies) {
        List<Pair> pairsOfGalaxies = new ArrayList<>();
        List<Galaxy> galaxyList = galaxies.stream().toList();
        for(int i=0;i<galaxyList.size();i++){
            for(int j=i+1; j<galaxyList.size();j++){
                pairsOfGalaxies.add(new Pair(galaxyList.get(i), galaxyList.get(j)));
            }
        }
        return pairsOfGalaxies;
    }

    private Set<Galaxy> getGalaxies() {
        Set<Galaxy> galaxies = new TreeSet<>();
        for (int i = 0; i < universe.length; i++) {
            for (int j = 0; j < universe[0].length; j++) {
                if (universe[i][j] > 0) {
                    galaxies.add(new Galaxy(universe[i][j], i, j, emptyRows, emptyCols, this.factorOfExpansion));
                }
            }
        }
        return galaxies;
    }


}
