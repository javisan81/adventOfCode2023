package day5;

import java.util.Objects;

public class Conversion {
    private final long sourceStartRange;
    private final long targetRange;
    private final long length;


    public Conversion(long sourceStartRange, long targetRange, long length) {
        this.sourceStartRange = sourceStartRange;
        this.targetRange = targetRange;
        this.length = length;
    }

    public long convert(long value) {
        return targetRange + (value - sourceStartRange);
    }

    public boolean accept(long input) {
        return input >= sourceStartRange && input < (sourceStartRange + length);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversion that = (Conversion) o;
        return sourceStartRange == that.sourceStartRange && targetRange == that.targetRange && length == that.length;
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceStartRange, targetRange, length);
    }

    @Override
    public String toString() {
        return "Conversion{" +
                "sourceStartRange=" + sourceStartRange +
                ", targetRange=" + targetRange +
                ", length=" + length +
                '}';
    }
}
