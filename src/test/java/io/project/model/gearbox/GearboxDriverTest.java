package io.project.model.gearbox;

import io.project.api.ExternalSystems;
import io.project.api.Gearbox;
import io.project.model.gashandler.GasThreshold;
import io.project.model.gearbox.calculator.GearCalculatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GearboxDriverTest {

    private Gearbox gearbox = new Gearbox();
    private ExternalSystems externalSystems = new ExternalSystems();

    private GearboxACL gearboxACL;
    private RPMProvider rpmProvider;
    private GearboxDriver gearboxDriver;
    private GearCalculatorFactory gearCalculatorFactory = new GearCalculatorFactory();

    @BeforeEach
    void setUp() {
        gearbox.setGearBoxCurrentParams(new Object[]{1, 1});
        gearbox.setMaxDrive(8);
        gearboxACL = new GearboxACL(gearbox);

        externalSystems.setCurrentRpm(2000d);
        rpmProvider = new RPMProvider(externalSystems);
        gearboxDriver = new GearboxDriver(gearboxACL, rpmProvider, gearCalculatorFactory);
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
}
