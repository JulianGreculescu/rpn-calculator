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
import static au.com.gritmed.rpn.domain.Operation.CLEAR;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ClearOperationTest {
    private OperationExecutor operation;

    @BeforeEach
    void setUp() {
        operation = new ClearOperation();
    }

    @Test
    void shouldBeAbleToClearOperandsStuck() {
        Cache calculatorCache = SampleDataSource.threeOperandsCache();

        operation.updateCache(calculatorCache, TEN);

        Optional<UndoLogEntry> oUndoLogEntry = calculatorCache.getUndoLog().pop();
        assertTrue(oUndoLogEntry.isPresent());
        oUndoLogEntry.ifPresent(undoLogEntry -> {
            assertEquals(CLEAR, undoLogEntry.getOperation());
            assertArrayEquals(new Operand[] { THREE, TWO, ONE }, undoLogEntry.getOperands());
        });
    }

    @Test
    void shouldDoNothingIfEmptyOperandsStuck() {
        Cache calculatorCache = new Cache();

        operation.updateCache(calculatorCache, TEN);

        Optional<UndoLogEntry> oUndoLogEntry = calculatorCache.getUndoLog().pop();
        assertFalse(oUndoLogEntry.isPresent());
    }
}
