package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.Operand;
import au.com.gritmed.rpn.domain.Operation;

import java.math.RoundingMode;
import java.util.Optional;
import java.util.function.BiFunction;

import static java.math.BigDecimal.ZERO;

public class DivideOperation implements TwoArgsOperation {
    private static final Optional<String> DECISION_BY_ZERO = Optional.of("Division by zero. Illegal operation.");

    @Override
    public Operation getOperation() {
        return Operation.DIVIDE;
    }

    @Override
    public BiFunction<Operand, Operand, Operand> getBiFunction() {
        return (x, y) -> new Operand(x.getValue().divide(y.getValue(), 15, RoundingMode.HALF_UP));
    }

    @Override
    public Optional<String> validateOperands(Operand... operands) {
        return ZERO.equals(operands[1].getValue()) ? DECISION_BY_ZERO : Optional.empty();
    }
}
