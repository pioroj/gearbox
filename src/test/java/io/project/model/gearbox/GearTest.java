package io.project.model.gearbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GearTest {

    @Test
    void cannotHaveGearWithNegativeRepresentation() {
        assertThrows(IllegalArgumentException.class, () -> new Gear(-2));
    }

    @Test
    void shouldCreateHigherGear() {
        assertEquals(new Gear(5), new Gear(4).upshift());
        assertEquals(new Gear(3), new Gear(2).upshift());
        assertEquals(new Gear(2), new Gear(1).upshift());
    }

    @Test
    void shouldCreateLowerGear() {
        assertEquals(new Gear(5), new Gear(6).downshift());
        assertEquals(new Gear(3), new Gear(4).downshift());
        assertEquals(new Gear(2), new Gear(3).downshift());
    }
}
