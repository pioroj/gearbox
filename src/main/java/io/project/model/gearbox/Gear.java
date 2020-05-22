package io.project.model.gearbox;

import java.util.Objects;

public class Gear {

    private final Integer numRepresentation;

    Gear(Integer numRepresentation) {
        if (numRepresentation < 0) {
            throw new IllegalArgumentException("Negative gear: " + numRepresentation);
        }
        this.numRepresentation = numRepresentation;
    }

    public static Gear of(int gear) {
        return new Gear(gear);
    }

    Gear upshift() {
        return of(numRepresentation + 1);
    }

    Gear downshift() {
        return of(numRepresentation - 1);
    }

    boolean greaterThanOrEqualsTo(Gear gear) {
        return numRepresentation.compareTo(gear.numRepresentation) >= 0;
    }

    boolean lessOrEqualsTo(Gear gear) {
        return numRepresentation.compareTo(gear.numRepresentation) <= 0;
    }

    int toIntValue() {
        return numRepresentation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Gear gear = (Gear) o;
        return Objects.equals(numRepresentation, gear.numRepresentation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numRepresentation);
    }
}
