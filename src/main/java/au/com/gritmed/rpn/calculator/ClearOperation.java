package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.Operand;
import au.com.gritmed.rpn.domain.Operation;
import au.com.gritmed.rpn.domain.UndoLogEntry;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static au.com.gritmed.rpn.domain.Operation.CLEAR;

public class ClearOperation implements OperationExecutor {
    private static final Optional<String> OPTIONAL_EMPTY = Optional.empty();
    @Override
    public Operation getOperation() {
        return CLEAR;
    }

    @Override
    public Optional<String> updateCache(Cache cache, int position) {
        OperandsStack operands = cache.getOperands();
        Optional<Operand> oOperand;
        List<Operand> undoList = new ArrayList<>();
        do {
            oOperand = operands.pop();
            oOperand.ifPresent(undoList::add);
        } while (oOperand.isPresent());

        if (!undoList.isEmpty()) {
            cache.getUndoLog().push(new UndoLogEntry(CLEAR, undoList.toArray(new Operand[0])));
        }
        return OPTIONAL_EMPTY;
    }
}
