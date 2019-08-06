

package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.Operand;
import au.com.gritmed.rpn.domain.UndoLogEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static au.com.gritmed.rpn.calculator.SampleDataSource.FOUR;
import static au.com.gritmed.rpn.calculator.SampleDataSource.ONE;
import static au.com.gritmed.rpn.calculator.SampleDataSource.TEN;
import static au.com.gritmed.rpn.calculator.SampleDataSource.THREE;
import static au.com.gritmed.rpn.calculator.SampleDataSource.TWO;
import static au.com.gritmed.rpn.domain.Operation.ADD;
import static au.com.gritmed.rpn.domain.Operation.CLEAR;
import static au.com.gritmed.rpn.domain.Operation.SQRT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class UndoOperationTest {
    private OperationExecutor operation;


    @BeforeEach
    void setUp() {
        operation = new UndoOperation();
    }

    @Test
    void shoulBeAbleToUndoOperation() {
        Cache calulatorCache = new Cache();
        calulatorCache.getOperands().push(THREE);
        calulatorCache.getUndoLog().push(new UndoLogEntry(ADD, new Operand[] { ONE, TWO } ));

        operation.updateCache(calulatorCache, TEN);

        Optional<Operand> second = calulatorCache.getOperands().pop();
        Optional<Operand> first = calulatorCache.getOperands().pop();
        assertTrue(second.isPresent());
        assertTrue(first.isPresent());
        assertEquals(ONE, first.get());
        assertEquals(TWO, second.get());
    }

    @Test
    void shouldBeAbleToUndoMultipleOperations() {
        Cache calulatorCache = new Cache();
        calulatorCache.getOperands().push(TWO);
        calulatorCache.getUndoLog().push(new UndoLogEntry(ADD, new Operand[] { THREE, ONE } ));
        calulatorCache.getUndoLog().push(new UndoLogEntry(SQRT, new Operand[] { FOUR } ));

        operation.updateCache(calulatorCache, TEN);
        Optional<Operand> first = calulatorCache.getOperands().pop();
        Optional<Operand> second = calulatorCache.getOperands().pop();
        assertTrue(first.isPresent());
        assertFalse(second.isPresent());
        assertEquals(FOUR, first.get());

        operation.updateCache(calulatorCache, TEN);
        second = calulatorCache.getOperands().pop();
        first = calulatorCache.getOperands().pop();
        assertTrue(first.isPresent());
        assertTrue(second.isPresent());
        assertEquals(ONE, second.get());
        assertEquals(THREE, first.get());
    }

    @Test
    void shouldBeAbleToUndoClaeanOperation() {
        Cache calulatorCache = new Cache();
        calulatorCache.getUndoLog().push(new UndoLogEntry(CLEAR, new Operand[] { FOUR, THREE, TWO, ONE } ));

        operation.updateCache(calulatorCache, TEN);
        Optional<Operand> first = calulatorCache.getOperands().pop();
        Optional<Operand> second = calulatorCache.getOperands().pop();
        Optional<Operand> third = calulatorCache.getOperands().pop();
        Optional<Operand>  forth = calulatorCache.getOperands().pop();
        assertTrue(first.isPresent());
        assertTrue(second.isPresent());
        assertTrue(third.isPresent());
        assertTrue(forth.isPresent());
        assertEquals(ONE, first.get());
        assertEquals(TWO, second.get());
        assertEquals(THREE, third.get());
        assertEquals(FOUR, forth.get());

    }
}
