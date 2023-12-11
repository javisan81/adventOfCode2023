package day10;

import java.util.Optional;

public enum Direction {
    EAST(0, 1), SOUTH(1, 0), WEST(0, -1), NORTH(-1, 0), ;
    int rowDelta;
    int colDelta;

    Direction(int rowDelta, int colDelta) {
        this.rowDelta = rowDelta;
        this.colDelta = colDelta;
    }

    public Optional<Position> move(Position currentPosition) {
        return currentPosition.move(this);
    }
}
