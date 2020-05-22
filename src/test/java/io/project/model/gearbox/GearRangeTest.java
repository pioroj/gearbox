package io.project.model.gearbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GearRangeTest {

    GearRange oneToEight = new GearRange(Gear.of(1), Gear.of(8));

    @Test
    void cannotCreateInvalidRange() {
        assertThrows(IllegalArgumentException.class, () -> new GearRange(Gear.of(2), Gear.of(1)));
    }

    @Test
    void shouldUpshiftGearInRange() {
        assertEquals(Gear.of(8), oneToEight.upshift(Gear.of(7)));
    }

    @Test
    void shouldDownshiftGearInRange() {
        assertEquals(Gear.of(1), oneToEight.downshift(Gear.of(2)));
    }
}
