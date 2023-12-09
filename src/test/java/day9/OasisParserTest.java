package day9;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OasisParserTest {
    @Test
    void oneLine() {
        OasisParser oasisParser = new OasisParser();
        assertEquals(
                new Oasis(
                        List.of(
                                List.of(0, 3, 6, 9, 12, 15)
                        )
                ),
                oasisParser.parse(List.of("0   3   6   9  12  15"))
        );
    }
}
