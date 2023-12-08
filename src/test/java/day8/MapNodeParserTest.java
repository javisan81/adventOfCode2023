package day8;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MapNodeParserTest {
    @Test
    void oneInstructionOneNode() {
        MapNodeParser mapNodeParser = new MapNodeParser(
                List.of(
                        "LRLRRRLRRLRLRRRLRRLLRRRLRLRLRLRRLRRRLRRLLRLRLRRRLRLLRRLRRLRLLRRLLRRLRRRLLRRLRRLRRRLRRRLRRRLRLRRLRRRLLRRLRRLRRRLRLRRRLRRLRRRLRRRLRLRLRLRLRLRLLRRLLLLRLRRRLRRRLLRRLRLRLLRRRLRLRRLRRRLLLLRRRLLRRLRRLRRLLRLLLLRLRRRLRLRRLRRLLRRRLRRLRLRRLRRRLLRRRLLRLRRLRRLLRRRLLRLRRLRLRRLLLRRRR",
                        "",
                        "RJK = (DPP, JQR)"
                )
        );
        assertEquals(new MapNodes(List.of(new Node("RJK", "DPP", "JQR"))), mapNodeParser.parse());
        assertEquals("LRLRRRLRRLRLRRRLRRLLRRRLRLRLRLRRLRRRLRRLLRLRLRRRLRLLRRLRRLRLLRRLLRRLRRRLLRRLRRLRRRLRRRLRRRLRLRRLRRRLLRRLRRLRRRLRLRRRLRRLRRRLRRRLRLRLRLRLRLRLLRRLLLLRLRRRLRRRLLRRLRLRLLRRRLRLRRLRRRLLLLRRRLLRRLRRLRRLLRLLLLRLRRRLRLRRLRRLLRRRLRRLRLRRLRRRLLRRRLLRLRRLRRLLRRRLLRLRRLRLRRLLLRRRR", mapNodeParser.getInstructions());
    }

    @Test
    void multipleNodes() {
        MapNodeParser mapNodeParser = new MapNodeParser(
                List.of(
                        "LRLRRRLRRLRLRRRLRRLLRRRLRLRLRLRRLRRRLRRLLRLRLRRRLRLLRRLRRLRLLRRLLRRLRRRLLRRLRRLRRRLRRRLRRRLRLRRLRRRLLRRLRRLRRRLRLRRRLRRLRRRLRRRLRLRLRLRLRLRLLRRLLLLRLRRRLRRRLLRRLRLRLLRRRLRLRRLRRRLLLLRRRLLRRLRRLRRLLRLLLLRLRRRLRLRRLRRLLRRRLRRLRLRRLRRRLLRRRLLRLRRLRRLLRRRLLRLRRLRLRRLLLRRRR",
                        "",
                        "RJK = (DPP, JQR)",
                        "QLH = (CXC, MXS)"
                )
        );
        assertEquals(
                new MapNodes(
                        List.of(
                                new Node("RJK", "DPP", "JQR"),
                                new Node("QLH", "CXC", "MXS")
                        )
                ), mapNodeParser.parse());
    }
}
