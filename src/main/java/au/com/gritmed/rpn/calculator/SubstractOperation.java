package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.Operand;
import au.com.gritmed.rpn.domain.Operation;

import java.util.function.BiFunction;

public class SubstractOperation implements TwoArgsOperation {
    @Override
    public Operation getOperation() {
        return Operation.SUBSTRACT;
    }

    @Override
    public BiFunction<Operand, Operand, Operand> getBiFunction() {
        return (x, y) -> new Operand(x.getValue().subtract(y.getValue()));
    }
}
