package day10;

import java.util.*;
import java.util.stream.Collectors;

import static day10.TileType.*;

public class PipeMaze {

    private final TileType[][] map;

    public PipeMaze(TileType[][] tileTypes) {
        this.map = tileTypes;
    }

    public int farthestPointInLoop() {
        Position startingPosition = findStartingPosition();
        List<PositionDirection> loop = findLoop(startingPosition);
        if (loop.isEmpty())
            return 0;
        return loop.size() / 2 + 1;
    }

    private List<PositionDirection> findLoop(Position startingPosition) {
        return findLoop(startingPosition, new ArrayList<>(), Direction.WEST);
    }

    private List<PositionDirection> findLoop(Position position, List<PositionDirection> positionsOfPipesUsed, Direction currentDirection) {
        TileType tileType = getTile(position);
        if (tileType == START && !positionsOfPipesUsed.isEmpty()) {
            return positionsOfPipesUsed;
        } else if (tileType == START) {
            Direction[] directions = Direction.values();
            for (Direction direction : directions) {
                List<PositionDirection> stepsToGoToStart = pipesToGoToStart(positionsOfPipesUsed, position.move(direction), direction);
                if (!stepsToGoToStart.isEmpty()) {
                    return stepsToGoToStart;
                }
            }
        } else if (tileType.isAPipe()) {
            Optional<PositionDirection> newPosition = tileType.move(position, currentDirection);
            return newPosition
                    .map(np -> findLoop(np.position(), addStep(positionsOfPipesUsed, new PositionDirection(position, currentDirection)), np.direction()))
                    .orElse(new ArrayList<>());
        }
        return new ArrayList<>();
    }

    private List<PositionDirection> pipesToGoToStart(List<PositionDirection> positionOfPipesUsed, Optional<Position> nextPositionInLoop, Direction currentDirection) {
        return nextPositionInLoop
                .stream()
                .flatMap(np -> findLoop(np, positionOfPipesUsed, currentDirection).stream())
                .collect(Collectors.toList());
    }

    private static List<PositionDirection> addStep(List<PositionDirection> stepsDone, PositionDirection nextPositionInLoop) {
        List<PositionDirection> newStepsDone = new ArrayList<>(stepsDone);
        newStepsDone.add(nextPositionInLoop);
        return newStepsDone;
    }


    private Position findStartingPosition() {
        for (int row = 0; row < map.length; row++) {
            for (int col = 0; col < map[row].length; col++) {
                if (map[row][col] == START) {
                    return new Position(row, col, maxRows(), maxCols());
                }
            }
        }
        return new Position(0, 0, maxRows(), maxCols());
    }

    private int maxCols() {
        return map[0].length;
    }

    private int maxRows() {
        return map.length;
    }

    public int tilesEnclosedInLoop() {
        Position startingPosition = findStartingPosition();
        List<PositionDirection> loop = findLoop(startingPosition);
        TileType connectorToReplaceS = getConnectorToReplaceS(loop);
        int maxRows = map.length;
        int maxCols = map[0].length;
        int[][] tilesEnclosedOrNot = new int[maxRows][maxCols];
        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                tilesEnclosedOrNot[row][col] = 0;
                Optional<Position> position = Optional.of(new Position(row, col, maxRows, maxCols));
                if (!isGround(position) && isNotPartOfTheLoop(loop, position)) {
                    map[row][col] = GROUND;
                }
            }
        }
        map[startingPosition.row()][startingPosition.col()] = connectorToReplaceS;

        for (int row = 0; row < maxRows; row++) {
            for (int col = 0; col < maxCols; col++) {
                Position position = new Position(row, col, maxRows, maxCols);
                TileType tileType = getTile(position);
                if (tileType == GROUND) {
                    if (numberOfRayIntersectionsWithLoop(position) % 2 == 1) {
                        tilesEnclosedOrNot[position.row()][position.col()] = 1;
                    }
                }
            }
        }

        int result = 0;
        for (int i = 0; i < maxRows; i++) {
            for (int j = 0; j < maxCols; j++) {
                result = result + tilesEnclosedOrNot[i][j];
            }
        }
        return result;
    }

    private TileType getConnectorToReplaceS(List<PositionDirection> loop) {
        TileType firstTile = getTile(loop.get(0).position());
        TileType lastTile = getTile(loop.get(loop.size() - 1).position());
        if (firstTile == HORIZONTAL && lastTile == VERTICAL) {
            return F;
        } else if (firstTile == VERTICAL && lastTile == F) {
            return SEVEN;
        } else if (firstTile == SEVEN && lastTile == J) {
            return F;
        }
        return J;
    }

    private int numberOfRayIntersectionsWithLoop(Position position) {
        int intersections = 0;
        for (int col = position.col() + 1; col < maxCols(); col++) {
            Position newPosition = new Position(position.row(), col, maxRows(), maxCols());
            if (intersects(newPosition)) {
                intersections++;
            }
        }
        return intersections;
    }

    private boolean intersects(Position newPosition) {
        List<TileType> allowedTypes = List.of(VERTICAL, J, L);
        return allowedTypes.contains(getTile(newPosition));
    }

    private boolean isNotPartOfTheLoop(List<PositionDirection> loop, Optional<Position> position) {
        if (getTile(position.get()) == START) {
            return false;
        }
        return loop.stream().noneMatch(pd -> pd.position().equals(position.get()));
    }


    private Boolean isGround(Optional<Position> nextTile) {
        return nextTile.map(t -> getTile(t) == TileType.GROUND).orElse(false);
    }


    private TileType getTile(Position p) {
        if (p.isInsideMap()) {
            return map[p.row()][p.col()];
        }
        return HORIZONTAL;
    }
}
