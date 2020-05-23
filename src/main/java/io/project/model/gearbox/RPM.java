package io.project.model.gearbox;

import java.util.Objects;

public class RPM implements Comparable<RPM> {

    private final Long rpm;

    RPM(long rpm) {
        if (rpm < 0) {
            throw new IllegalArgumentException("Negative RPM: " + rpm);
        }
        this.rpm = rpm;
    }

    public static RPM k(double k) {
        return RPM.rpm((long) (k * 1000));
    }

    public static RPM rpm(long rpm) {
        return new RPM(rpm);
    }

    public static RPM rpm(double rpm) {
        return new RPM((long) rpm);
    }

    public boolean isAbove(RpmRange rpmRange) {
        return rpmRange.endSmallerThan(this);
    }

    public boolean isBelow(RpmRange rpmRange) {
        return rpmRange.startGreaterThan(this);
    }

    public boolean isBelow(RPM otherRPM) {
        return this.rpm < otherRPM.rpm;
    }

    RPM multiplyBy(double multiplier) {
        long newRPM = Math.round(this.rpm * multiplier);
        return new RPM(newRPM);
    }

    @Override
    public int compareTo(RPM other) {
        return this.rpm.compareTo(other.rpm);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RPM rpm1 = (RPM) o;
        return Objects.equals(rpm, rpm1.rpm);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rpm);
    }
}
