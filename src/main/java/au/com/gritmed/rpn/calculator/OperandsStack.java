package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.Operand;

import java.util.EmptyStackException;
import java.util.Optional;
import java.util.Stack;

import static java.util.stream.Collectors.joining;

public class OperandsStack {
    private final static String STACK = "stack: ";
    private Stack<Operand> operands = new Stack<>();

    public void push(Operand operand) {
       operands.push(operand);
    }

    public Optional<Operand> pop() {
        try {
            return Optional.of(operands.pop());
        } catch (EmptyStackException e) {
            return Optional.empty();
        }
    }

    public int clear() {
        int cleared = operands.size();
        operands.clear();
        return cleared;
    }

    @Override
    public String toString() {
        return (STACK + operands.stream().map(Operand::toString).collect(joining(" "))).trim();
    }
}
