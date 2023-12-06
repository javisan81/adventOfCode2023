package day3;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Engine {
    private final char[][] schematic;

    public Engine(char[][] schematic) {
        this.schematic = schematic;
    }



    class Position {
        final int row;
        final int col;

        public Position(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Position() {
            this.row = -1;
            this.col = -1;
        }

        @Override
        public String toString() {
            return "Position{" +
                    "row=" + row +
                    ", col=" + col +
                    '}';
        }
    }

    class Symbol {
        final Position position;
        final char symbol;

        public Symbol(Position position, char symbol) {
            this.position = position;
            this.symbol = symbol;
        }
    }

    class Number {
        final Position positionStart;
        final Position positionEnd;

        final int number;

        public Number(Position positionStart, Position positionEnd, int number) {
            this.positionStart = positionStart;
            this.positionEnd = positionEnd;
            this.number = number;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Number number1 = (Number) o;
            return number == number1.number;
        }

        @Override
        public int hashCode() {
            return Objects.hash(number);
        }

        @Override
        public String toString() {
            return "Number{" +
                    "positionStart=" + positionStart +
                    ", positionEnd=" + positionEnd +
                    ", number=" + number +
                    '}';
        }

        public boolean isAdjacent(Symbol symbol) {
            int startingRow = positionStart.row - 1;
            int endingRow = positionStart.row + 1;
            int startingCol = positionStart.col - 1;
            int endingCol = positionEnd.col + 1;
            return symbol.position.col>= startingCol && symbol.position.col<=endingCol && symbol.position.row>=startingRow && symbol.position.row<=endingRow;
        }
    }

    public int calculateGears() {
        List<Number> numbers = getPartNumbers();
        return getSymbols().stream()
                .map(symbol -> calculateGearFactor(symbol, numbers))
                .mapToInt(Integer::intValue)
                .sum();
    }

    private Integer calculateGearFactor(Symbol symbol, List<Number> numbers) {
        List<Number> numbersAdjacentToSymbol = numbers.stream()
                .filter(n -> n.isAdjacent(symbol))
                .toList();
        if(numbersAdjacentToSymbol.size()<=1){
            return 0;
        }
        return numbersAdjacentToSymbol.stream()
                .mapToInt(n -> n.number)
                .reduce(1, (a,b) -> a*b);
    }

    private List<Symbol> getSymbols() {
        List<Symbol> symbols = new ArrayList<>();
        for (int countRows = 0; countRows < schematic.length; countRows++) {
            for (int countCols = 0; countCols < schematic[countRows].length; countCols++) {
                char character = schematic[countRows][countCols];
                if (isSymbol(character)) {
                    symbols.add(new Symbol( new Position(countRows, countCols), character));
                }
            }
        }
        return symbols;
    }

    public int calculateSumSchematics() {
        return getPartNumbers().stream()
                .mapToInt(number -> number.number)
                .sum();
    }

    private List<Number> getPartNumbers() {
        return getNumbers().stream()
                .filter(number1 -> isAPartNumber(schematic, number1))
                .collect(Collectors.toList());
    }

    private List<Number> getNumbers() {
        List<Number> numbersToSum = new ArrayList<>();
        for (int countRows = 0; countRows < schematic.length; countRows++) {
            String number = "";
            Position positionStart = new Position();
            Position positionEnd = new Position();
            for (int countCols = 0; countCols < schematic[countRows].length; countCols++) {
                char character = schematic[countRows][countCols];
                if (Character.isDigit(character)) {
                    if (number.equals("")) {
                        positionStart = new Position(countRows, countCols);
                    }
                    positionEnd = new Position(countRows, countCols);
                    number = number + character;
                } else if (!number.equals("")) {
                    numbersToSum.add(new Number(positionStart, positionEnd, Integer.parseInt(number)));
                    number = "";
                    positionStart = new Position();
                    positionEnd = new Position();
                }
            }
            if (!number.equals("")) {
                numbersToSum.add(new Number(positionStart, positionEnd, Integer.parseInt(number)));
            }
        }
        return numbersToSum;
    }

    private boolean isAPartNumber(char[][] schematic, Number number) {
        int startingRow = number.positionStart.row - 1;
        int endingRow = number.positionStart.row + 1;
        int startingCol = number.positionStart.col - 1;
        int endingCol = number.positionEnd.col + 1;
        for (int row = startingRow; row <= endingRow; row++) {
            for (int col = startingCol; col <= endingCol; col++) {
                if (isValidRange(row, col) && isSymbol(schematic[row][col])) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean isValidRange(int row, int col) {
        return col >= 0 && row >= 0 && row < schematic.length && col < schematic[0].length;
    }

    private boolean isSymbol(char c) {
        return !Character.isDigit(c) && '.' != c;
    }
}
