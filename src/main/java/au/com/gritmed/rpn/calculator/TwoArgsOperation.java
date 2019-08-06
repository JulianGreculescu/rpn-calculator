package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.Operand;
import au.com.gritmed.rpn.domain.UndoLogEntry;

import java.util.Optional;
import java.util.function.BiFunction;

import static java.lang.String.format;

public interface TwoArgsOperation extends OperationExecutor {

    default Optional<String> updateCache(Cache cache, int position) {
        Optional<Operand> oSecond = cache.getOperands().pop();
        if (oSecond.isPresent()) {
            Optional<Operand> oFirst = cache.getOperands().pop();
            if (oFirst.isPresent()) {
                Optional<String> oError = validateOperands(oFirst.get(), oSecond.get());
                if (oError.isPresent()) {
                    cache.getOperands().push(oFirst.get());
                    cache.getOperands().push(oSecond.get());
                } else {
                    Operand result = getBiFunction().apply(oFirst.get(), oSecond.get());
                    cache.getOperands().push(result);
                    cache.getUndoLog().push(new UndoLogEntry(getOperation(), new Operand[] { oSecond.get(), oFirst.get() }));
                }
                return oError;
            } else {
                cache.getOperands().push(oSecond.get());
            }
        }

        return Optional.of(format("Operator %s (position: %d): insuficient parameters", getOperation().getSymbol(), position));
    }

    BiFunction<Operand, Operand, Operand> getBiFunction();
}
