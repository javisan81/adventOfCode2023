package day6;

import day5.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParseAlmanacTest {
    @Test
    void readString() throws URISyntaxException, IOException {
        String rawAlmanac = Files.readString(Path.of(ClassLoader.getSystemResource("smallAlmanac.txt").toURI()));
        ParseAlmanac parseAlmanac = new ParseAlmanac(rawAlmanac);
        assertEquals(new Almanac(
                        List.of(
                                new Conversions(
                                        "seed",
                                        "soil",
                                        List.of(
                                                new Conversion(98, 50, 2),
                                                new Conversion(50, 52, 48)
                                        )
                                ),
                                new Conversions(
                                        "soil",
                                        "fertilizer",
                                        List.of(
                                                new Conversion(15, 0, 37),
                                                new Conversion(52, 37, 2),
                                                new Conversion(0, 39, 15)
                                        )
                                ),
                                new Conversions(
                                        "fertilizer",
                                        "water",
                                        List.of(
                                                new Conversion(53, 49, 8),
                                                new Conversion(11, 0, 42),
                                                new Conversion(0, 42, 7),
                                                new Conversion(7, 57, 4)
                                        )
                                ),
                                new Conversions(
                                        "water",
                                        "light",
                                        List.of(
                                                new Conversion(18, 88, 7),
                                                new Conversion(25, 18, 70)
                                        )
                                ),
                                new Conversions(
                                        "light",
                                        "temperature",
                                        List.of(
                                                new Conversion(77, 45, 23),
                                                new Conversion(45, 81, 19),
                                                new Conversion(64, 68, 13)
                                        )
                                ),
                                new Conversions(
                                        "temperature",
                                        "humidity",
                                        List.of(
                                                new Conversion(69, 0, 1),
                                                new Conversion(0, 1, 69)
                                        )
                                ),
                                new Conversions(
                                        "humidity",
                                        "location",
                                        List.of(
                                                new Conversion(56, 60, 37),
                                                new Conversion(93, 56, 4)
                                        )
                                )
                        ),
                        new ListSeeds(List.of(79L, 14L, 55L, 13L))
                ),
                parseAlmanac.parse()
        );
    }

}
