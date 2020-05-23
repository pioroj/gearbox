package io.project.model.gearbox.adapter;

import io.project.api.ExternalSystems;

public class VehicleAngleProvider {

    private final ExternalSystems externalSystems;

    public VehicleAngleProvider(ExternalSystems externalSystems) {
        this.externalSystems = externalSystems;
    }

    public boolean isVehicleDrivingDown() {
        Integer lightsPosition = externalSystems.getLights().getLightsPosition();
        return lightsPosition == 1 || lightsPosition == 2 || lightsPosition == 3;
    }
    
}
