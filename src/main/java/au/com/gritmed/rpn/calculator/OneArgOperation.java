package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.Operand;
import au.com.gritmed.rpn.domain.UndoLogEntry;

import java.util.Optional;
import java.util.function.Function;

import static java.lang.String.format;

public interface OneArgOperation extends OperationExecutor {

    default Optional<String> updateCache(Cache cache, int position) {
        Optional<Operand> oArg = cache.getOperands().pop();
        if (oArg.isPresent()) {
            Operand operand = oArg.get();
            Optional<String> oError = validateOperands(operand);
            if (oError.isPresent()) {
                cache.getOperands().push(operand);
            } else {
                Operand result = getFunction().apply(operand);
                cache.getOperands().push(result);
                cache.getUndoLog().push(new UndoLogEntry(getOperation(), new Operand[] { operand }));
            }
            return oError;
        }

        return Optional.of(format("Operator %s (position: %d): insuficient parameters", getOperation().getSymbol(), position));
    }

    Function<Operand, Operand> getFunction();
}
