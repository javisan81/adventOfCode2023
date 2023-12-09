package day9;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class OasisParser {
    public Oasis parse(List<String> lines) {
        return new Oasis(
                lines.stream()
                        .map(OasisParser::toIntegers)
                        .collect(Collectors.toList())
        );
    }

    private static List<Integer> toIntegers(String l) {
        return Stream.of(l.split(" ")).filter(Predicate.not(String::isEmpty)).map(Integer::parseInt).collect(Collectors.toList());
    }
}
