package day10;

import java.util.Optional;

public record Position(int row, int col, int maxRows, int maxCols) {
    public Optional<Position> move(Direction direction) {
        int newRow = row + direction.rowDelta;
        int newCol = col + direction.colDelta;
        return move(newRow, newCol);
    }

    public boolean isInsideMap() {
        return row>=0 && row<maxRows && col>=0 && col<maxCols;
    }

    public Optional<Position> move(Delta delta) {
        int newRow = row + delta.deltaRow();
        int newCol = col + delta.deltaCol();
        return move(newRow, newCol);
    }

    private Optional<Position> move(int newRow, int newCol) {
        if (newRow < 0 || newRow >= maxRows) {
            return Optional.empty();
        } else if (newCol < 0 || newCol >= maxCols) {
            return Optional.empty();
        }
        return Optional.of(new Position(newRow, newCol, maxRows, maxCols));
    }
}
