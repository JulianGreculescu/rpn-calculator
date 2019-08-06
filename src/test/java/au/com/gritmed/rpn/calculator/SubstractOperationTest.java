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
import static au.com.gritmed.rpn.domain.Operation.SUBSTRACT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SubstractOperationTest {
    private OperationExecutor operation;

    @BeforeEach
    void setUp() {
        operation = new SubstractOperation();
    }

    @Test
    void shouldCorrectlySubstractTwoOperands() {
        Cache calculatorCache = SampleDataSource.twoOperandsCache();

        assertFalse(operation.updateCache(calculatorCache, TEN).isPresent());
        assertEquals("stack: -1", calculatorCache.getOperands().toString());
        Optional<UndoLogEntry> oUndoLogEntry = calculatorCache.getUndoLog().pop();
        assertTrue(oUndoLogEntry.isPresent());
        assertEquals(new UndoLogEntry(SUBSTRACT, new Operand[] { TWO, ONE }), oUndoLogEntry.get());
    }

    @Test
    void shouldCorrectlySubstractLastTwoOperands() {
        Cache calculatorCache = SampleDataSource.threeOperandsCache();

        assertFalse(operation.updateCache(calculatorCache, TEN).isPresent());
        assertEquals("stack: 1 -1", calculatorCache.getOperands().toString());
        Optional<UndoLogEntry> oUndoLogEntry = calculatorCache.getUndoLog().pop();
        assertTrue(oUndoLogEntry.isPresent());
        assertEquals(new UndoLogEntry(SUBSTRACT, new Operand[] { THREE, TWO }), oUndoLogEntry.get());
    }

    @Test
    void shouldReportInsuficiantParameters() {
        Cache calculatorCache = SampleDataSource.oneOperansCache();

        Optional<String> oErrorMsg = operation.updateCache(calculatorCache, TEN);
        assertTrue(oErrorMsg.isPresent());
        assertEquals("Operator - (position: 10): insuficient parameters", oErrorMsg.get());
        assertEquals("stack: 1", calculatorCache.getOperands().toString());
    }
}
