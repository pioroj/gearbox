package io.project.model.gearbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RpmRangeTest {

    @Test
    void shouldCreateInvalidRange() {
        assertThrows(IllegalArgumentException.class, () -> new RpmRange(RPM.k(4), RPM.k(3)));
    }

    @Test
    void shouldCreateValidRange() {
        assertDoesNotThrow(() -> new RpmRange(RPM.k(3), RPM.k(4)));
    }

}
