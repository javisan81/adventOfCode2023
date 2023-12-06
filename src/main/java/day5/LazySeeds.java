package day5;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class LazySeeds implements Seeds {
    private final String rawAlmanac;

    public LazySeeds(String rawAlmanac) {
        this.rawAlmanac = rawAlmanac;
    }


    private List<Long> getSeeds() {
        return Arrays.stream(
                        rawAlmanac
                                .substring(rawAlmanac.lastIndexOf("seeds:"), rawAlmanac.indexOf("\n\n"))
                                .replace("seeds:", "")
                                .split(" ")
                )
                .filter(Predicate.not(String::isEmpty))
                .filter(s -> !s.equals("\n") && !s.equals("\r"))
                .map(Long::parseLong)
                .collect(Collectors.toList());
    }

    private Stream<Long> generateSeeds(long firstValue, long length) {
        return LongStream.range(firstValue, firstValue + length)
                .boxed();
    }

    @Override
    public Stream<Long> stream() {
        Stream<Long> results = Stream.of();
        List<Long> seedsInRanges = getSeeds();
        for (int i = 0; i < seedsInRanges.size(); i = i + 2) {
            long firstValue = seedsInRanges.get(i);
            long range = seedsInRanges.get(i + 1);
            results = Stream.concat(results, generateSeeds(firstValue, range));
        }
        return results;
    }
}
