package io.project.model.gearbox.calculator;

import io.project.model.gearbox.AggressiveMode;
import io.project.model.gearbox.GasThreshold;
import io.project.model.gearbox.GasThresholdRange;
import io.project.model.gearbox.Gear;
import io.project.model.gearbox.GearRange;
import io.project.model.gearbox.RPM;
import io.project.model.gearbox.RpmRange;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SportGearCalculatorTest {

    private SportGearCalculator sportGearCalculator = new SportGearCalculator(
            new RpmRange(RPM.k(1.5), RPM.k(5)),
            new GearRange(Gear.of(1), Gear.of(8)),
            GasThresholdRange.doubleKickdownRange(new GasThreshold(0.5), new GasThreshold(0.7)),
            RPM.rpm(3000));

    @Test
    void shouldKickdownWhenGasThresholdReached() {
        Gear nextGear = sportGearCalculator.calculateGear(
                new CalculatorInputData(RPM.rpm(2200), Gear.of(4), new GasThreshold(0.6d), AggressiveMode.MEDIUM, 150, false, false));

        assertEquals(Gear.of(3), nextGear);
    }

    @Test
    void shouldDoubleKickdownWhenGasThresholdReached() {
        Gear nextGear = sportGearCalculator.calculateGear(
                new CalculatorInputData(RPM.rpm(2200), Gear.of(4), new GasThreshold(0.8d), AggressiveMode.MEDIUM, 150, false, false));

        assertEquals(Gear.of(2), nextGear);
    }

    @Test
    void shouldNotKickdownWhenGasThresholdNotReached() {
        Gear nextGear = sportGearCalculator.calculateGear(
                new CalculatorInputData(RPM.rpm(2200), Gear.of(4), new GasThreshold(0.3d), AggressiveMode.MEDIUM, 150, false, false));

        assertEquals(Gear.of(4), nextGear);
    }

    @Test
    void shouldShiftUpInBasicAggresiveMode() {
        Gear nextGear = sportGearCalculator.calculateGear(
                new CalculatorInputData(RPM.rpm(5600), Gear.of(4), new GasThreshold(0.3d), AggressiveMode.BASIC, 150, false, false));

        assertEquals(Gear.of(5), nextGear);
    }

    @Test
    void shouldAllowForHigherRPMInMediumAggressiveMode() {
        Gear nextGear = sportGearCalculator.calculateGear(
                new CalculatorInputData(RPM.rpm(2600), Gear.of(4), new GasThreshold(0.3d), AggressiveMode.MEDIUM, 150, false, false));

        assertEquals(Gear.of(4), nextGear);
    }

    @Test
    void shouldReduceGearWithTrailerAndRidingDown() {
        Gear nextGear = sportGearCalculator.calculateGear(
                new CalculatorInputData(RPM.rpm(2600), Gear.of(4), new GasThreshold(0.3d), AggressiveMode.MEDIUM, 150, true, true));

        assertEquals(Gear.of(3), nextGear);
    }
}
