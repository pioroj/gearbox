package io.project.model.gearbox;

import java.util.Objects;

public class RpmRange {

    private final double MEDIUM_AGGRESIVE_MODE_MULTIPLIER = 1.2d;
    private final double HIGH_AGGRESIVE_MODE_MULTIPLIER = 1.3d;

    private final RPM left;
    private final RPM right;

    public RpmRange(RPM min, RPM max) {
        if (min.compareTo(max) > 0) {
            throw new IllegalArgumentException("Wrong RpmRange - max lower than min");
        }
        this.left = min;
        this.right = max;
    }

    boolean startGreaterThan(RPM rpm) {
        return left.compareTo(rpm) > 0;
    }

    boolean endSmallerThan(RPM rpm) {
        return right.compareTo(rpm) < 0;
    }

    public RpmRange apply(AggressiveMode aggressiveMode) {
        switch (aggressiveMode) {
            case BASIC:
                return this;
            case MEDIUM:
                return new RpmRange(left.multiplyBy(MEDIUM_AGGRESIVE_MODE_MULTIPLIER), right.multiplyBy(MEDIUM_AGGRESIVE_MODE_MULTIPLIER));
            case HIGH:
                return new RpmRange(left.multiplyBy(HIGH_AGGRESIVE_MODE_MULTIPLIER), right.multiplyBy(HIGH_AGGRESIVE_MODE_MULTIPLIER));
        }
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RpmRange rpmRange = (RpmRange) o;
        return Objects.equals(left, rpmRange.left) &&
                Objects.equals(right, rpmRange.right);
    }

    @Override
    public int hashCode() {
        return Objects.hash(left, right);
    }
}
