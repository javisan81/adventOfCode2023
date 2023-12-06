package day1;

import java.util.List;
import java.util.Set;

public class PositionNameOrNumbers {
    class PositionName implements Comparable {
        int position;
        NameOfNumber nameOfNumber;

        public PositionName(int position, NameOfNumber nameOfNumber) {
            this.position = position;
            this.nameOfNumber = nameOfNumber;
        }

        @Override
        public int compareTo(Object o) {
            PositionName that = (PositionName) o;
            return Integer.compare(this.position, that.position);
        }
    }

    private final String line;
    private final Set<NameOfNumber> nameAndNumbers;

    public PositionNameOrNumbers(String line, Set<NameOfNumber> nameAndNumbers) {
        this.line = line;
        this.nameAndNumbers = nameAndNumbers;
    }

    public List<NameOfNumber> orderedNamesAndNumbers() {
        return nameAndNumbers.stream()
                .map(nameOfNumber -> new PositionName(line.indexOf(nameOfNumber.getName()), nameOfNumber))
                .sorted()
                .map(positionName -> positionName.nameOfNumber)
                .toList();
    }
}
