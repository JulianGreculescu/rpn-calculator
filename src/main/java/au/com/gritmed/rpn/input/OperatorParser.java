package au.com.gritmed.rpn.input;

import au.com.gritmed.rpn.domain.Operation;
import au.com.gritmed.rpn.domain.Operator;

import java.util.Optional;

public final class OperatorParser {
    private OperatorParser() {
    }

    public static Optional<InputToken> parse(String token, int position) {
        try {
            return Optional.of(new Operator(Operation.ofSymbol(token), position));
        } catch (IllegalArgumentException e) {
            return Optional.empty();
        }
    }
}
