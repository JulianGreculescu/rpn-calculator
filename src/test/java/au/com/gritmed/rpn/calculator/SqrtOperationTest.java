package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.Operand;
import au.com.gritmed.rpn.domain.UndoLogEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Optional;

import static au.com.gritmed.rpn.calculator.SampleDataSource.ONE;
import static au.com.gritmed.rpn.calculator.SampleDataSource.TEN;
import static au.com.gritmed.rpn.calculator.SampleDataSource.THREE;
import static au.com.gritmed.rpn.domain.Operation.SQRT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SqrtOperationTest {
    private OperationExecutor operation;

    @BeforeEach
    void setUp() {
        operation = new SqrtOperation();
    }

    @Test
    void shouldCorrectlyPerformSqrt() {
        Cache calculatorCache = SampleDataSource.oneOperansCache();

        assertFalse(operation.updateCache(calculatorCache, TEN).isPresent());
        assertEquals("stack: 1", calculatorCache.getOperands().toString());
        Optional<UndoLogEntry> oUndoLogEntry = calculatorCache.getUndoLog().pop();
        assertTrue(oUndoLogEntry.isPresent());
        assertEquals(new UndoLogEntry(SQRT, new Operand[] { ONE }), oUndoLogEntry.get());
    }

    @Test
    void shouldCorrectlyPerformSqrtOnLastOperand() {
        Cache calculatorCache = SampleDataSource.threeOperandsCache();

        assertFalse(operation.updateCache(calculatorCache, TEN).isPresent());
        assertEquals("stack: 1 2 1.7320508075", calculatorCache.getOperands().toString());
        Optional<UndoLogEntry> oUndoLogEntry = calculatorCache.getUndoLog().pop();
        assertTrue(oUndoLogEntry.isPresent());
        assertEquals(new UndoLogEntry(SQRT, new Operand[] { THREE }), oUndoLogEntry.get());
    }

    @Test
    void shouldReportInsuficiantParameters() {
        Cache calculatorCache = new Cache();

        Optional<String> oErrorMsg = operation.updateCache(calculatorCache, TEN);
        assertTrue(oErrorMsg.isPresent());
        assertEquals("Operator sqrt (position: 10): insuficient parameters", oErrorMsg.get());
         assertEquals("stack:", calculatorCache.getOperands().toString());
    }

    @Test
    void shoulDetectNegativeOperand() {
        Cache calculatorCache = SampleDataSource.oneOperansCache();
        calculatorCache.getOperands().push(new Operand(new BigDecimal(-5)));

        Optional<String> oErrorMsg = operation.updateCache(calculatorCache, TEN);
        assertTrue(oErrorMsg.isPresent());
        assertEquals("Negative argument. Illegal operation.", oErrorMsg.get());
        assertEquals("stack: 1 -5", calculatorCache.getOperands().toString());
    }
}
