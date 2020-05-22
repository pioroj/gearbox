package io.project.model.gashandler;

import java.util.Objects;

public class GasThreshold {

    private static final double MIN_LEVEL = 0;
    private static final double MAX_LEVEL = 1.0d;

    private double pressLevel;

    public GasThreshold(double pressLevel) {
        if (pressLevel < MIN_LEVEL || pressLevel > MAX_LEVEL) {
            throw new IllegalArgumentException("Press level must be form " + MIN_LEVEL + " to " + MAX_LEVEL);
        }
        this.pressLevel = pressLevel;
    }

    public static GasThreshold max() {
        return new GasThreshold(1.0d);
    }

    double getPressLevel() {
        return pressLevel;
    }

    public boolean lowerThan(GasThreshold gasThreshold) {
        return this.getPressLevel() < gasThreshold.getPressLevel();
    }

    public boolean greaterThanOrEqualsTo(GasThreshold gasThreshold) {
        return this.getPressLevel() >= gasThreshold.getPressLevel();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GasThreshold that = (GasThreshold) o;
        return Double.compare(that.pressLevel, pressLevel) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(pressLevel);
    }
}
