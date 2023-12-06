package day5;

import java.util.List;
import java.util.Objects;

public class Conversions {
    private final String sourceType;
    private final String targetType;

    private final List<Conversion> conversions;

    public Conversions(String sourceType, String targetType, List<Conversion> conversions) {
        this.sourceType = sourceType;
        this.targetType = targetType;
        this.conversions = conversions;
    }

    public ConversionInput convert(ConversionInput conversionInput) {
        if (conversionInput.type().equals(sourceType)) {
            for (Conversion conversion : conversions) {
                if (conversion.accept(conversionInput.value())) {
                    return new ConversionInput(conversion.convert(conversionInput.value()), targetType);
                }
            }
            return new ConversionInput(conversionInput.value(), targetType);
        } else {
            return conversionInput;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conversions that = (Conversions) o;
        return Objects.equals(sourceType, that.sourceType) && Objects.equals(targetType, that.targetType) && Objects.equals(conversions, that.conversions);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sourceType, targetType, conversions);
    }

    @Override
    public String toString() {
        return "Conversions{" +
                "sourceType='" + sourceType + '\'' +
                ", targetType='" + targetType + '\'' +
                ", conversions=" + conversions +
                '}';
    }
}
