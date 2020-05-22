package io.project.model.gearbox.adapter;

import io.project.api.ExternalSystems;
import io.project.model.gearbox.RPM;

public class RPMProvider {

    private final ExternalSystems externalSystems;

    public RPMProvider(ExternalSystems externalSystems) {
        this.externalSystems = externalSystems;
    }

    public RPM current() {
        return RPM.rpm(externalSystems.getCurrentRpm());
    }

}
