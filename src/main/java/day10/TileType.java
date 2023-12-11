package day10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

public enum TileType {
    VERTICAL('|', true, List.of(Direction.NORTH, Direction.SOUTH), Function.identity()),
    HORIZONTAL('-', true, List.of(Direction.EAST, Direction.WEST), Function.identity()),
    L('L', true, List.of(Direction.SOUTH, Direction.WEST), direction -> direction == Direction.SOUTH ? Direction.EAST : Direction.NORTH),
    J('J', true, List.of(Direction.SOUTH, Direction.EAST), direction -> direction == Direction.SOUTH ? Direction.WEST : Direction.NORTH),
    SEVEN('7', true, List.of(Direction.NORTH, Direction.EAST), direction -> direction == Direction.NORTH ? Direction.WEST : Direction.SOUTH),
    F('F', true, List.of(Direction.NORTH, Direction.WEST), direction -> direction == Direction.NORTH ? Direction.EAST : Direction.SOUTH),
    GROUND('.', false, new ArrayList<>(), Function.identity()),
    START('S', false, new ArrayList<>(), Function.identity());

    private final char character;
    private final boolean isAPipe;
    private final List<Direction> compatibleDirections;
    private final Function<Direction, Direction> newDirection;

    TileType(char character, boolean isPipe, List<Direction> compatibleDirections, Function<Direction, Direction> newDirection) {
        this.character = character;
        this.isAPipe = isPipe;
        this.compatibleDirections = compatibleDirections;
        this.newDirection = newDirection;
    }

    static TileType from(char character) {
        return Arrays.stream(TileType.values())
                .filter(tt -> tt.character == character)
                .findFirst()
                .orElse(GROUND);
    }

    public boolean isAPipe() {
        return isAPipe;
    }


    public Optional<PositionDirection> move(Position position, Direction directionTaken) {
        if (this.isCompatible(directionTaken)) {
            Direction newDirection = this.newDirection.apply(directionTaken);
            return newDirection.move(position).map(p -> new PositionDirection(p, newDirection));
        } else {
            return Optional.empty();
        }
    }

    public Direction newDirection(Direction currentDirection) {
        return newDirection.apply(currentDirection);
    }

    private boolean isCompatible(Direction directionTaken) {
        return compatibleDirections.contains(directionTaken);
    }

    public boolean isCorner() {
        return switch (this){
            case J, L, F, SEVEN -> true;
            default -> false;
        };
    }
}
