package io.project.model.gearbox;

import java.util.Objects;

public class RpmRange {

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
