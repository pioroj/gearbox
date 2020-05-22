package io.project.model.gearbox.calculator;

import io.project.model.gashandler.GasThreshold;
import io.project.model.gearbox.AggressiveMode;
import io.project.model.gearbox.GasThresholdRange;
import io.project.model.gearbox.Gear;
import io.project.model.gearbox.GearRange;
import io.project.model.gearbox.RPM;
import io.project.model.gearbox.RpmRange;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ComfortGearCalculatorTest {

    private ComfortGearCalculator comfortGearCalculator = new ComfortGearCalculator(
            new RpmRange(RPM.k(1), RPM.k(2.5)),
            new GearRange(Gear.of(1), Gear.of(8)),
            GasThresholdRange.singleKickdownRange(new GasThreshold(0.5)));

    @Test
    void shouldShiftUpWhenAboveMaxRpm() {
        Gear nextGear = comfortGearCalculator.calculateGear(RPM.rpm(3200), Gear.of(6), new GasThreshold(0.3d), AggressiveMode.MEDIUM);

        assertEquals(Gear.of(7), nextGear);
    }

    @Test
    void shouldShiftDownWhenBelowMinRpm() {
        Gear nextGear = comfortGearCalculator.calculateGear(RPM.rpm(800), Gear.of(6), new GasThreshold(0.3d), AggressiveMode.MEDIUM);

        assertEquals(Gear.of(5), nextGear);
    }

    @Test
    void shouldDoNothingWhenWithinOptimalRpm() {
        Gear nextGear = comfortGearCalculator.calculateGear(RPM.rpm(2200), Gear.of(6), new GasThreshold(0.3d), AggressiveMode.MEDIUM);

        assertEquals(Gear.of(6), nextGear);
    }

    @Test
    void shouldDoNothingWhenMaxGearReached() {
        Gear nextGear = comfortGearCalculator.calculateGear(RPM.rpm(3200), Gear.of(8), new GasThreshold(0.3d), AggressiveMode.MEDIUM);

        assertEquals(Gear.of(8), nextGear);
    }

    @Test
    void shouldDoNothingWhenMinGearReached() {
        Gear nextGear = comfortGearCalculator.calculateGear(RPM.rpm(1200), Gear.of(1), new GasThreshold(0.3d), AggressiveMode.MEDIUM);

        assertEquals(Gear.of(1), nextGear);
    }

    @Test
    void shouldKickdownWhenGasThresholdReached() {
        Gear nextGear = comfortGearCalculator.calculateGear(RPM.rpm(2200), Gear.of(4), new GasThreshold(0.6d), AggressiveMode.MEDIUM);

        assertEquals(Gear.of(3), nextGear);
    }

    @Test
    void shouldNotKickdownWhenGasThresholdNotReached() {
        Gear nextGear = comfortGearCalculator.calculateGear(RPM.rpm(2200), Gear.of(4), new GasThreshold(0.3d), AggressiveMode.MEDIUM);

        assertEquals(Gear.of(4), nextGear);
    }

    @Test
    void shouldShiftUpInBasicAggresiveMode() {
        Gear nextGear = comfortGearCalculator.calculateGear(RPM.rpm(2600), Gear.of(4), new GasThreshold(0.3d), AggressiveMode.BASIC);

        assertEquals(Gear.of(5), nextGear);
    }

    @Test
    void shouldAllowForHigherRPMInMediumAggressiveMode() {
        Gear nextGear = comfortGearCalculator.calculateGear(RPM.rpm(2600), Gear.of(4), new GasThreshold(0.3d), AggressiveMode.MEDIUM);

        assertEquals(Gear.of(4), nextGear);
    }
}
