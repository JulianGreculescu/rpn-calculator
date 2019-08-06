package au.com.gritmed.rpn.input;

import au.com.gritmed.rpn.domain.Operand;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperandParserTest {

    @ParameterizedTest
    @ValueSource(strings = { "123.12345678901234", "+123.12345678901234", "-123.12345678901234" })
    void shouldBeAbleToParseNumbers(String token) {
        Optional<InputToken> oInputToken = OperandParser.parse(token);
        assertTrue(oInputToken.isPresent());
        assertTrue(oInputToken.get() instanceof Operand);
    }

    @ParameterizedTest
    @ValueSource(strings = { "+", "Not a number" })
    void shouldReturnNothingIfNotANumber(String notANumber) {
        assertFalse(OperandParser.parse(notANumber).isPresent());
    }
}
