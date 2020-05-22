package io.project.model.gearbox.adapter;

import io.project.api.ExternalSystems;

public class AngularSpeedProvider {

    private final ExternalSystems externalSystems;

    public AngularSpeedProvider(ExternalSystems externalSystems) {
        this.externalSystems = externalSystems;
    }

    public double current() {
        return externalSystems.getAngularSpeed();
    }
}
