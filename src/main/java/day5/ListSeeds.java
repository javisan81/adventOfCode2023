package day5;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

public class ListSeeds implements Seeds {
    private final List<Long> seeds;

    public ListSeeds(List<Long> seeds) {
        this.seeds = seeds;
    }

    @Override
    public Stream<Long> stream() {
        return seeds.stream();
    }

    @Override
    public String toString() {
        return "ListSeeds{" +
                "seeds=" + seeds +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ListSeeds listSeeds = (ListSeeds) o;
        return Objects.equals(seeds, listSeeds.seeds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(seeds);
    }
}
