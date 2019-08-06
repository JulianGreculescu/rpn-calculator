package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.Operand;
import au.com.gritmed.rpn.domain.Operation;

import java.util.Optional;

/**
 * Describes a type of computation supported by the RPN calculator
 * such as +, -, sqrt, undo
 */
public interface OperationExecutor {
    /**
     * @return the operator associated with this operation
     */
    Operation getOperation();

    /**
     * This where is operator logic gets executed.
     *
     * @param cache the calculator cahe storing current calculation data
     * @param position the position of the operation curentrl being executed
     *
     * @return optionally an error message if the computation could not be performed.
     * The cache stays untouch if an error is returned. No error means the computation
     * was performed.
     */
    Optional<String> updateCache(Cache cache, int position);

    /**
     * Use thus to validate operands An exaple of an invalid operand
     * is a ZERO disvisor in a divide oeration.
     *
     * @param operands the operands to validate
     * @return optionally an error message when invalid operator(s) are found
     */
    default Optional<String> validateOperands(Operand... operands) {
        return Optional.empty();
    }
}
