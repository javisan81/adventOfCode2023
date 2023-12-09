package day9;

import java.util.function.IntBinaryOperator;

public enum Predict {
    NEXT(Integer::sum), PREVIOUS((a, b) -> b - a);
    final IntBinaryOperator reducer;

    Predict(IntBinaryOperator reducer) {
        this.reducer = reducer;
    }
}
