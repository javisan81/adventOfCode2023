package day1;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.IntStream;

public class Trebuchet {
    private static Set<NameOfNumber> nameAndNumbers = new TreeSet<>(IntStream.range(1, 10).mapToObj(NameOfNumber::new).toList());

    public List<Integer> calibration(List<String> lines) {
        return lines.stream().map(this::transformToNumbers).map(this::calibration).toList();
    }

    private String transformToNumbers(String line) {
        String result = line;
        while (existAnyNumberAsText(result)) {
            var orderedNameAndNumbers = new PositionNameOrNumbers(line, nameAndNumbers);
            List<NameOfNumber> nameOfNumbersOrdered = orderedNameAndNumbers.orderedNamesAndNumbers();
            for (var entry : nameOfNumbersOrdered) {
                result = result.replaceFirst(entry.getName(), "" + entry.getNumber() + entry.getName().charAt(entry.getName().length()-1));
            }
        }
        return result;
    }

    private static boolean existAnyNumberAsText(String result) {
        return nameAndNumbers.stream().anyMatch(n -> result.contains(n.getName()));
    }

    private int calibration(String line) {
        Character firstDigit = null;
        char lastDigit = '0';
        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);
            if (Character.isDigit(currentChar)) {
                if (firstDigit == null) {
                    firstDigit = currentChar;
                }
                lastDigit = currentChar;
            }
        }
        if (firstDigit == null) {
            return 0;
        }
        return Integer.parseInt("" + firstDigit + lastDigit);
    }
}
