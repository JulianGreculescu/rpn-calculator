package au.com.gritmed.rpn.input;

import java.util.Optional;
import java.util.stream.Stream;

public final class InputTokenParser {
    private InputTokenParser() {
    }

    public static Optional<InputToken> parse(String token, int position) {
        return Stream.of(
                OperandParser.parse(token),
                OperatorParser.parse(token, position)
        ).parallel()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .findFirst();
    }
}
