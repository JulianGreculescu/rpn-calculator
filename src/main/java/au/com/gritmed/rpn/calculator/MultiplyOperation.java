package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.Operand;
import au.com.gritmed.rpn.domain.Operation;

import java.util.function.BiFunction;

public class MultiplyOperation implements TwoArgsOperation {
    @Override
    public Operation getOperation() {
        return Operation.MULTIPLY;
    }

    @Override
    public BiFunction<Operand, Operand, Operand> getBiFunction() {
        return (x, y) -> new Operand(x.getValue().multiply(y.getValue()));
    }
}
