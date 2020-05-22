package io.project.model.gearbox.adapter;

import io.project.api.Gearbox;
import io.project.model.gearbox.Gear;

public class GearboxACL {

    private final Gearbox gearbox;

    public GearboxACL(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    public void changeGearTo(Gear newGear) {
        gearbox.setCurrentGear(newGear.toIntValue());
    }

    public Gear currentGear() {
        return new Gear((Integer) gearbox.getCurrentGear());
    }

    public Gear getMaxDrive() {
        return new Gear(gearbox.getMaxDrive());
    }
}
