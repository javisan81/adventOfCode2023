package day5;

import java.util.List;
import java.util.Objects;

public class Almanac {
    private final List<Conversions> conversions;
    private final Seeds seeds;

    public Almanac(List<Conversions> conversions, Seeds seeds) {
        this.conversions = conversions;
        this.seeds = seeds;
    }


    public long toLowestLocation() {
        return seeds.stream()
                .parallel()
                .mapToLong(this::convert)
                .min()
                .orElse(Integer.MAX_VALUE);
    }

    private long convert(Long value) {
        ConversionInput result = new ConversionInput(value, "seed");
        for (Conversions conversion : conversions) {
            result = conversion.convert(result);
        }
        return result.value();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Almanac almanac = (Almanac) o;
        return Objects.equals(conversions, almanac.conversions) && Objects.equals(seeds, almanac.seeds);
    }

    @Override
    public int hashCode() {
        return Objects.hash(conversions, seeds);
    }

    @Override
    public String toString() {
        return "Almanac{" +
                "conversions=" + conversions +
                ", seeds=" + seeds +
                '}';
    }
}
