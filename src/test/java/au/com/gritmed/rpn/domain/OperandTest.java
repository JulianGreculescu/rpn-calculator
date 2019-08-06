package au.com.gritmed.rpn.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OperandTest {
    @Test
    void shouldNotAcceptNullArgumentConstructor() {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> new Operand(null));
        assertEquals("Operand cannot be null", thrown.getMessage());
    }

    @Test
    void shoulOverrideToString() {
        assertEquals("0", new Operand(new BigDecimal("-0.0")).toString());
        assertEquals("0.0123456789", new Operand(new BigDecimal("0.01234567890123456789")).toString());
        assertEquals("0.012345", new Operand(new BigDecimal("0.012345")).toString());
        assertEquals("0.0123456789", new Operand(new BigDecimal("0.01234567899")).toString());
    }
}