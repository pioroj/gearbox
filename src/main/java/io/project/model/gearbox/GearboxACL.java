package io.project.model.gearbox;

import io.project.api.Gearbox;

class GearboxACL {

    private final Gearbox gearbox;

    GearboxACL(Gearbox gearbox) {
        this.gearbox = gearbox;
    }

    void changeGearTo(Gear newGear) {
        gearbox.setCurrentGear(newGear.toIntValue());
    }

    Gear currentGear() {
        return new Gear((Integer) gearbox.getCurrentGear());
    }

    Gear getMaxDrive() {
        return new Gear(gearbox.getMaxDrive());
    }
}
