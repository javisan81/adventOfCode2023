package day5;

import day5.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import static java.lang.Long.parseLong;

public class ParseAlmanac {
    private final String rawAlmanac;

    public ParseAlmanac(String rawAlmanac) {
        this.rawAlmanac = rawAlmanac;
    }

    public Almanac parse() {
        return new Almanac(getConversions(), new ListSeeds(getSeeds()));
    }

    public Almanac parse2() {
        return new Almanac(getConversions(), new LazySeeds(rawAlmanac));
    }

    private List<Long> getSeedsByPairs() {
        List<Long> results = new ArrayList<>();
        List<Long> seedsInRanges = getSeeds();
        for(int i=0;i<seedsInRanges.size(); i = i+2){
            long firstValue = seedsInRanges.get(i);
            long range = seedsInRanges.get(i+1);
            results.addAll(generateSeeds(firstValue, range));
        }
        return results;
    }

    private List<Long> generateSeeds(long firstValue, long length) {
        return LongStream.range(firstValue, firstValue+length)
                .boxed()
                .collect(Collectors.toList());
    }

    private List<Conversions> getConversions() {
        var conversionsRaw = Arrays.stream(rawAlmanac.replaceAll("seeds: .*\n\n", "").split(".* map:"))
                .filter(Predicate.not(String::isEmpty))
                .toArray(String[]::new);
        return List.of(
                toConversion(conversionsRaw[0], "seed", "soil"),
                toConversion(conversionsRaw[1], "soil", "fertilizer"),
                toConversion(conversionsRaw[2], "fertilizer", "water"),
                toConversion(conversionsRaw[3], "water", "light"),
                toConversion(conversionsRaw[4], "light", "temperature"),
                toConversion(conversionsRaw[5], "temperature", "humidity"),
                toConversion(conversionsRaw[6], "humidity", "location")
        );
    }

    private Conversions toConversion(String conversionsRaw, String source, String target) {
        String[] lines = conversionsRaw.split("\n");
        return new Conversions(
                source,
                target,
                Arrays.stream(lines)
                        .filter(Predicate.not(String::isEmpty))
                        .map(this::getConversion)
                        .collect(Collectors.toList())
        );
    }

    private Conversion getConversion(String rawConversion) {
        String[] numbersRaw = Arrays.stream(rawConversion.split(" "))
                .filter(Predicate.not(String::isEmpty))
                .filter(s -> !s.equals("\n") && !s.equals("\r"))
                .toArray(String[]::new);
        return new Conversion(parseLong(numbersRaw[1]), parseLong(numbersRaw[0]), parseLong(numbersRaw[2]));
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
                .collect(Collectors.toList()
                );
    }

}
