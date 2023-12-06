package day5;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlmanacTest {
    @Test
    void noConversions() {
        Almanac almanac = new Almanac(
                List.of(),
                new ListSeeds(List.of(79L))
        );
        assertEquals(79, almanac.toLowestLocation());
    }

    @Test
    void oneConversion() {
        Almanac almanac = new Almanac(
                List.of(
                        new Conversions("seed",
                                "soil",
                                List.of(new Conversion(50, 52, 48)
                                )
                        )
                ),
                new ListSeeds(List.of(79L))
        );
        assertEquals(81, almanac.toLowestLocation());
    }

    @Test
    void twoConversionForSeed() {
        Almanac almanac = new Almanac(
                List.of(
                        new Conversions(
                                "seed",
                                "soil",
                                List.of(
                                        new Conversion(98, 50, 2),
                                        new Conversion(50, 52, 48)
                                )
                        )
                ),
                new ListSeeds(List.of(79L))
        );
        assertEquals(81, almanac.toLowestLocation());
    }

    @Test
    void bigTest() throws URISyntaxException, IOException {
        String rawAlmanac = Files.readString(Path.of(ClassLoader.getSystemResource("almanac.txt").toURI()));
        ParseAlmanac parseAlmanac = new ParseAlmanac(rawAlmanac);
        assertEquals(1181555926, parseAlmanac.parse().toLowestLocation());
    }

    @Test
    void secondPArt() throws URISyntaxException, IOException {
        String rawAlmanac = Files.readString(Path.of(ClassLoader.getSystemResource("almanac.txt").toURI()));
        ParseAlmanac parseAlmanac = new ParseAlmanac(rawAlmanac);
        assertEquals(37806486, parseAlmanac.parse2().toLowestLocation());
    }
}
