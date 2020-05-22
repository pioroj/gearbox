package io.project.model.gearbox;

public class GearRange {

    private final Gear first;
    private final Gear maxGear;

    public GearRange(Gear first, Gear maxGear) {
        if (first.greaterThanOrEqualsTo(maxGear)) {
            throw new IllegalArgumentException("Invalid range: " + first + " is greater than " + maxGear);
        }
        this.first = first;
        this.maxGear = maxGear;
    }

    public Gear upshift(Gear currentGear) {
        if (currentGear.greaterThanOrEqualsTo(maxGear)) {
            return currentGear;
        }
        return currentGear.upshift();
    }

    public Gear downshift(Gear currentGear) {
        if (currentGear.lessOrEqualsTo(first)) {
            return currentGear;
        }
        return currentGear.downshift();
    }
}
