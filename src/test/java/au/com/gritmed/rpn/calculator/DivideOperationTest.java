package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.Operand;
import au.com.gritmed.rpn.domain.UndoLogEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static au.com.gritmed.rpn.calculator.SampleDataSource.ONE;
import static au.com.gritmed.rpn.calculator.SampleDataSource.TEN;
import static au.com.gritmed.rpn.calculator.SampleDataSource.THREE;
import static au.com.gritmed.rpn.calculator.SampleDataSource.TWO;
import static au.com.gritmed.rpn.domain.Operation.DIVIDE;
import static java.math.BigDecimal.ZERO;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class DivideOperationTest {
    private OperationExecutor operation;

    @BeforeEach
    void setUp() {
        operation = new DivideOperation();
    }

    @Test
    void shouldCorrectlyDivideTwoOperands() {
        Cache calculatorCache = SampleDataSource.twoOperandsCache();

        assertFalse(operation.updateCache(calculatorCache, TEN).isPresent());
        assertEquals("stack: 0.5", calculatorCache.getOperands().toString());
        Optional<UndoLogEntry> oUndoLogEntry = calculatorCache.getUndoLog().pop();
        assertTrue(oUndoLogEntry.isPresent());
        assertEquals(new UndoLogEntry(DIVIDE, new Operand[] { TWO, ONE }), oUndoLogEntry.get());
    }

    @Test
    void shouldCorrectlyDivideLastTwoOperands() {
        Cache calculatorCache = SampleDataSource.threeOperandsCache();

        assertFalse(operation.updateCache(calculatorCache, TEN).isPresent());
        assertEquals("stack: 1 0.6666666666", calculatorCache.getOperands().toString());
        Optional<UndoLogEntry> oUndoLogEntry = calculatorCache.getUndoLog().pop();
        assertTrue(oUndoLogEntry.isPresent());
        assertEquals(new UndoLogEntry(DIVIDE, new Operand[] { THREE, TWO }), oUndoLogEntry.get());
    }

    @Test
    void shouldReportInsuficiantParameters() {
        Cache calculatorCache = SampleDataSource.oneOperansCache();

        Optional<String> oErrorMsg = operation.updateCache(calculatorCache, TEN);
        assertTrue(oErrorMsg.isPresent());
        assertEquals("Operator / (position: 10): insuficient parameters", oErrorMsg.get());
        assertEquals("stack: 1", calculatorCache.getOperands().toString());
    }

    @Test
    void shoulDetectDivisionByZero() {
        Cache calculatorCache = SampleDataSource.oneOperansCache();
        calculatorCache.getOperands().push(new Operand(ZERO));

        Optional<String> oErrorMsg = operation.updateCache(calculatorCache, TEN);
        assertTrue(oErrorMsg.isPresent());
        assertEquals("Division by zero. Illegal operation.", oErrorMsg.get());
        assertEquals("stack: 1 0", calculatorCache.getOperands().toString());
    }
}
