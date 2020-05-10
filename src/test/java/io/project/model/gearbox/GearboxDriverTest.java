package io.project.model.gearbox;

import io.project.api.ExternalSystems;
import io.project.api.Gearbox;
import io.project.model.gashandler.GasThreshold;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GearboxDriverTest {

    private GearboxDriver gearboxDriver;
    private Gearbox gearbox = new Gearbox();
    private ExternalSystems externalSystems = new ExternalSystems();

    @BeforeEach
    void setUp() {
        gearbox.setGearBoxCurrentParams(new Object[]{2, 0});
        gearbox.setMaxDrive(8);
        externalSystems.setCurrentRpm(2000d);
        gearboxDriver = new GearboxDriver(gearbox, externalSystems);
    }

    @Test
    void shouldUpshiftWhenRpmAreHigh() {
        GasThreshold gasThreshold = new GasThreshold(50);
        gearbox.setGearBoxCurrentParams(new Object[]{1, 2});
        externalSystems.setCurrentRpm(2500d);

        gearboxDriver.handleAccelerationWith(gasThreshold);

        assertEquals(gearbox.getCurrentGear(), 3);
    }

    @Test
    void shouldDownshiftWhenRpmAreLow() {
        GasThreshold gasThreshold = new GasThreshold(50);
        gearbox.setGearBoxCurrentParams(new Object[]{1, 2});
        externalSystems.setCurrentRpm(900d);

        gearboxDriver.handleAccelerationWith(gasThreshold);

        assertEquals(gearbox.getCurrentGear(), 1);
    }
}
