package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.Operation;
import au.com.gritmed.rpn.domain.UndoLogEntry;

import java.util.Arrays;
import java.util.Optional;

import static au.com.gritmed.rpn.domain.Operation.CLEAR;
import static au.com.gritmed.rpn.domain.Operation.UNDO;

public class UndoOperation implements OperationExecutor {
    private static final Optional<String> OPTIONAL_EMPTY = Optional.empty();

    @Override
    public Operation getOperation() {
        return UNDO;
    }

    @Override
    public Optional<String> updateCache(Cache cache, int position) {
        Optional<UndoLogEntry> oUndoLogEntry = cache.getUndoLog().pop();
        if (oUndoLogEntry.isPresent()) {
            UndoLogEntry undoLogEntry = oUndoLogEntry.get();
            if (CLEAR != undoLogEntry.getOperation()) {                    // Clear is the only omne undoable oeration which does not produce any result.
                // For all others take out the result before butting back the operands from
                // undo log. Do not worry about pop result it can be anything including empty
                // in the case of nothing to undo
                cache.getOperands().pop();
            }
            Arrays.stream(undoLogEntry.getOperands()).forEach(o -> cache.getOperands().push(o));
        } else {
            cache.getOperands().pop();
        }

        return OPTIONAL_EMPTY;
    }
}
