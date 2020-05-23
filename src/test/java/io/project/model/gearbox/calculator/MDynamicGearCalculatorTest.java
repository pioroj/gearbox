package io.project.model.gearbox.calculator;

import io.project.model.gearbox.AggressiveMode;
import io.project.model.gearbox.GasThreshold;
import io.project.model.gearbox.Gear;
import io.project.model.gearbox.RPM;
import io.project.model.gearbox.adapter.AngularSpeedRange;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MDynamicGearCalculatorTest {

    private MDynamicGearCalculator mDynamicGearCalculator = new MDynamicGearCalculator(new AngularSpeedRange(100, 200));

    @Test
    void shouldNotModifyGears() {
        Gear nextGear = mDynamicGearCalculator.calculateGear(
                new CalculatorInputData(RPM.rpm(2600), Gear.of(4), new GasThreshold(0.3d), AggressiveMode.MEDIUM, 150, false, false));

        assertEquals(Gear.of(4), nextGear);
    }
}
