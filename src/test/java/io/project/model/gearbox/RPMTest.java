package io.project.model.gearbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RPMTest {

    @Test
    void name() {
        assertThrows(IllegalArgumentException.class, () -> RPM.rpm(-2));
    }

    @Test
    void kilosShouldBeEqualToUnits() {
        assertEquals(new RPM(2500), RPM.k(2.5));
        assertEquals(new RPM(800), RPM.k(0.8));
    }
}
