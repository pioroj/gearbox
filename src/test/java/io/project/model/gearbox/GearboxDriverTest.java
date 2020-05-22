package io.project.model.gearbox;

import io.project.api.ExternalSystems;
import io.project.api.Gearbox;
import io.project.model.gashandler.GasThreshold;
import io.project.model.gearbox.adapter.AngularSpeedProvider;
import io.project.model.gearbox.adapter.GearboxACL;
import io.project.model.gearbox.adapter.RPMProvider;
import io.project.model.gearbox.calculator.GearCalculatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GearboxDriverTest {

    private Gearbox gearbox = new Gearbox();
    private ExternalSystems externalSystems = new ExternalSystems();

    private GearboxACL gearboxACL;
    private RPMProvider rpmProvider;
    private AngularSpeedProvider angularSpeedProvider;
    private GearboxDriver gearboxDriver;
    private GearCalculatorFactory gearCalculatorFactory = new GearCalculatorFactory();

    @BeforeEach
    void setUp() {
        gearbox.setGearBoxCurrentParams(new Object[]{1, 1});
        gearbox.setMaxDrive(8);
        gearboxACL = new GearboxACL(gearbox);

        externalSystems.setCurrentRpm(2000d);
        rpmProvider = new RPMProvider(externalSystems);
        angularSpeedProvider = new AngularSpeedProvider(externalSystems);
        gearboxDriver = new GearboxDriver(gearboxACL, rpmProvider, angularSpeedProvider, gearCalculatorFactory);
    }

    @Test
    void shouldUpshiftWhenRpmAreHighInComfortMode() {
        GasThreshold gasThreshold = new GasThreshold(0.2d);
        gearbox.setGearBoxCurrentParams(new Object[]{1, 2});
        externalSystems.setCurrentRpm(2600d);

        gearboxDriver.handleAccelerationWith(gasThreshold);

        assertEquals(3, gearbox.getCurrentGear());
    }

    @Test
    void shouldDownshiftWhenRpmAreLowInComfortMode() {
        GasThreshold gasThreshold = new GasThreshold(0.2d);
        gearbox.setGearBoxCurrentParams(new Object[]{1, 2});
        externalSystems.setCurrentRpm(900d);

        gearboxDriver.handleAccelerationWith(gasThreshold);

        assertEquals(gearbox.getCurrentGear(), 1);
    }

    @Test
    void shouldManualChangeGearDown() {
        gearbox.setCurrentGear(2);
        Gear gear = gearboxDriver.manualChangeGearDown();

        assertEquals(1, gear.toIntValue());
    }

    @Test
    void shouldManualChangeGearUp() {
        gearbox.setCurrentGear(2);
        Gear gear = gearboxDriver.manualChangeGearUp();

        assertEquals(3, gear.toIntValue());
    }

    @Test
    void shouldManualChangeGearDown_notExceedingGearboxLimit() {
        gearbox.setCurrentGear(1);
        Gear gear = gearboxDriver.manualChangeGearDown();

        assertEquals(1, gear.toIntValue());
    }

    @Test
    void shouldManualChangeGearUp_notExceedingGearboxLimit() {
        gearbox.setCurrentGear(8);
        Gear gear = gearboxDriver.manualChangeGearUp();

        assertEquals(8, gear.toIntValue());
    }
}
