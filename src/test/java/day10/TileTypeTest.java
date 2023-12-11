package day10;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class TileTypeTest {
    @Test
    void southLToEast() {
        Position zeroZero = new Position(0, 0, 100, 100);
        assertEquals(Optional.of(new PositionDirection(new Position(0, 1, 100, 100), Direction.EAST)), TileType.L.move(zeroZero, Direction.SOUTH));
    }

    @Test
    void westLToNorth() {
        Position position = new Position(1, 1, 100, 100);
        assertEquals(Optional.of(new PositionDirection(new Position(0, 1, 100, 100), Direction.NORTH)), TileType.L.move(position, Direction.WEST));
    }

    @Test
    void eastJToNorth() {
        Position position = new Position(1, 1, 100, 100);
        assertEquals(Optional.of(new PositionDirection(new Position(0, 1, 100, 100), Direction.NORTH)), TileType.J.move(position, Direction.EAST));
    }

    @Test
    void southJToWest() {
        Position position = new Position(1, 1, 100, 100);
        assertEquals(Optional.of(new PositionDirection(new Position(1, 0, 100, 100), Direction.WEST)), TileType.J.move(position, Direction.SOUTH));
    }

    @Test
    void east7ToSouth() {
        Position position = new Position(1, 1, 100, 100);
        assertEquals(Optional.of(new PositionDirection(new Position(2, 1, 100, 100), Direction.SOUTH)), TileType.SEVEN.move(position, Direction.EAST));
    }

    @Test
    void north7ToWest() {
        Position position = new Position(1, 1, 100, 100);
        assertEquals(Optional.of(new PositionDirection(new Position(1, 0, 100, 100), Direction.WEST)), TileType.SEVEN.move(position, Direction.NORTH));
    }

    @Test
    void westFToSouth() {
        Position position = new Position(1, 1, 100, 100);
        assertEquals(Optional.of(new PositionDirection(new Position(2, 1, 100, 100), Direction.SOUTH)), TileType.F.move(position, Direction.WEST));
    }

    @Test
    void northFToEast() {
        Position position = new Position(1, 1, 100, 100);
        assertEquals(Optional.of(new PositionDirection(new Position(1, 2, 100, 100), Direction.EAST)), TileType.F.move(position, Direction.NORTH));
    }

}