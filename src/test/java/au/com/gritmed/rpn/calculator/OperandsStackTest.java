package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.Operand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperandsStackTest {

    private OperandsStack operandsStack;

    @BeforeEach
    void setUp() {
        operandsStack = new OperandsStack();
    }

    @Test
    void shouldSupportPushAndPopOperations() {
        Operand toStack = new Operand(new BigDecimal("123.45"));
        operandsStack.push(toStack);
        Optional<Operand> fromStack =  operandsStack.pop();
        assertTrue(fromStack.isPresent());
        assertEquals(toStack, fromStack.get());
    }

    @Test
    void shouldOverrideToString() {
        operandsStack.push(new Operand(new BigDecimal(1)));
        operandsStack.push(new Operand(new BigDecimal(-2.01234567890123)));
        operandsStack.push(new Operand(new BigDecimal(3)));

        assertEquals("stack: 1 -2.012345679 3", operandsStack.toString());
    }

    @Test
    void shouldBeAbleToCleanTheStack() {
        assertEquals(0, operandsStack.clear());
        operandsStack.push(new Operand(new BigDecimal("1")));
        operandsStack.push(new Operand(new BigDecimal("2")));
        operandsStack.push(new Operand(new BigDecimal("1")));
        assertEquals(3, operandsStack.clear());
    }
}
