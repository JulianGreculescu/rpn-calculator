package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.Operand;
import au.com.gritmed.rpn.domain.Operation;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.function.Function;

import static java.math.BigDecimal.ZERO;

public class SqrtOperation implements OneArgOperation {
    private static final Optional<String> NEGATIVE_ARGUMENT = Optional.of("Negative argument. Illegal operation.");

    @Override
    public Operation getOperation() {
        return Operation.SQRT;
    }

    @Override
    public Function<Operand, Operand> getFunction() {
        return x -> new Operand(new BigDecimal(Math.sqrt(x.getValue().doubleValue())));
    }

    @Override
    public Optional<String> validateOperands(Operand... operands) {
        return operands[0].getValue().compareTo(ZERO) < 0 ? NEGATIVE_ARGUMENT : Optional.empty();
    }
}
