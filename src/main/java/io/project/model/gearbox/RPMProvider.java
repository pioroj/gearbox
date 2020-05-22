package io.project.model.gearbox;

import io.project.api.ExternalSystems;

class RPMProvider {

    private final ExternalSystems externalSystems;

    RPMProvider(ExternalSystems externalSystems) {
        this.externalSystems = externalSystems;
    }

    RPM current() {
        return RPM.rpm(externalSystems.getCurrentRpm());
    }

}
