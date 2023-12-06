package day1;

import java.util.Map;

public class NameOfNumber implements Comparable {
    private final static Map<Integer, String> NAMES = Map.of(
            0, "zero",
            1, "one",
            2, "two",
            3, "three",
            4, "four",
            5, "five",
            6, "six",
            7, "seven",
            8, "eight",
            9, "nine"
    );
    private final String name;
    private final int number;

    public NameOfNumber(int number) {
        this.name = NAMES.get(number);
        this.number = number;
    }


    @Override
    public int compareTo(Object o) {
        NameOfNumber that = (NameOfNumber) o;
        return Integer.compare(this.number, that.number);
    }

    public String getName() {
        return name;
    }

    public int getNumber() {
        return number;
    }
}
