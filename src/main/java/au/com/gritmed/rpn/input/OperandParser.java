package au.com.gritmed.rpn.input;

import au.com.gritmed.rpn.domain.Operand;

import java.math.BigDecimal;
import java.util.Optional;

public final class OperandParser {
    private OperandParser() {
    }

    public static Optional<InputToken> parse(String token) {
        try {
            return Optional.of(new Operand(new BigDecimal(token)));
        } catch (NumberFormatException e) {
            return Optional.empty();
        }

    }
}
