package day1;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrebuchetTest {

    @Test
    void emptyString(){
        Trebuchet trebuchet = new Trebuchet();
        assertEquals(List.of(0), trebuchet.calibration(List.of("")));
    }
    @Test
    void oneLineOneDigit(){
        Trebuchet trebuchet = new Trebuchet();
        assertEquals(List.of(11), trebuchet.calibration(List.of("1")));
    }
    @Test
    void oneLineTwoDigits(){
        Trebuchet trebuchet = new Trebuchet();
        assertEquals(List.of(12), trebuchet.calibration(List.of("12")));
    }

    @Test
    void oneLineSeveralChars(){
        Trebuchet trebuchet = new Trebuchet();
        assertEquals(List.of(12), trebuchet.calibration(List.of("1abc2")));
    }

    @Test
    void twoLines(){
        Trebuchet trebuchet = new Trebuchet();
        assertEquals(List.of(12, 38), trebuchet.calibration(List.of("1abc2", "pqr3stu8vwx")));
    }
    @Test
    void multipleLines(){
        Trebuchet trebuchet = new Trebuchet();
        assertEquals(List.of(12, 38, 15, 77),
                trebuchet.calibration(List.of("1abc2", "pqr3stu8vwx", "a1b2c3d4e5f", "treb7uchet")));
    }
    @Test
    void finalTest() throws IOException, URISyntaxException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("trebuchet.txt").toURI()));

        Trebuchet trebuchet = new Trebuchet();
        assertEquals(53221,
                trebuchet.calibration(lines).stream().mapToInt(Integer::intValue).sum());
    }
    @Test
    void oneAsLetter(){
        Trebuchet trebuchet = new Trebuchet();
        assertEquals(List.of(11), trebuchet.calibration(List.of("one")));
    }
    @Test
    void twentyNineAsLetter(){
        Trebuchet trebuchet = new Trebuchet();
        assertEquals(List.of(29), trebuchet.calibration(List.of("two1nine")));
    }

    @Test
    void zoneight234(){
        Trebuchet trebuchet = new Trebuchet();
        assertEquals(List.of(14), trebuchet.calibration(List.of("zoneight234")));
    }
    @Test
    void sevenpqrstsixteen(){
        Trebuchet trebuchet = new Trebuchet();
        assertEquals(List.of(76), trebuchet.calibration(List.of("7pqrstsixteen")));
    }

    @Test
    void xtwone3four(){
        Trebuchet trebuchet = new Trebuchet();
        assertEquals(List.of(24), trebuchet.calibration(List.of("xtwone3four")));
    }

    @Test
    void fournineeightseven2(){
        Trebuchet trebuchet = new Trebuchet();
        assertEquals(List.of(42), trebuchet.calibration(List.of("4nineeightseven2")));
    }

    @Test
    void oneightwone(){
        Trebuchet trebuchet = new Trebuchet();
        assertEquals(List.of(11), trebuchet.calibration(List.of("oneightwone")));
    }

    @Test
    void oneightwoneight(){
        Trebuchet trebuchet = new Trebuchet();
        assertEquals(List.of(18), trebuchet.calibration(List.of("oneightwoneight")));
    }

    @Test
    void eighthree(){
        Trebuchet trebuchet = new Trebuchet();
        assertEquals(List.of(83), trebuchet.calibration(List.of("eighthree")));
    }


    @Test
    void trebuchetSmall() throws IOException, URISyntaxException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("trebuchetSmall.txt").toURI()));

        Trebuchet trebuchet = new Trebuchet();
        assertEquals(List.of(29, 83, 13, 24, 42, 14, 76), trebuchet.calibration(lines));
    }



    @Test
    void refinalTest() throws IOException, URISyntaxException {
        List<String> lines = Files.readAllLines(Path.of(ClassLoader.getSystemResource("trebuchet2.txt").toURI()));

        Trebuchet trebuchet = new Trebuchet();
        assertEquals(53221,
                trebuchet.calibration(lines).stream().mapToInt(Integer::intValue).sum());
    }
}
