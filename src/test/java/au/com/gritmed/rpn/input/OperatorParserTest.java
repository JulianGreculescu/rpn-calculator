package au.com.gritmed.rpn.input;

import au.com.gritmed.rpn.domain.Operator;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class OperatorParserTest {

    private static final int TEN = 10;

    @ParameterizedTest
    @ValueSource(strings = {"+", "-", "*", "/", "SQRT", "Undo", "cLeaR"})
    void shouldBeAbleToParseOperators(String token) {
        Optional<InputToken> oInputToken = OperatorParser.parse(token, TEN);
        assertTrue(oInputToken.isPresent());
        assertTrue(oInputToken.get() instanceof Operator);
    }

    @ParameterizedTest
    @ValueSource(strings = {"123", "abs"})
    void shouldReturnNothingIfNotAnOperator(String notAnOperator) {
        assertFalse(OperatorParser.parse(notAnOperator, TEN).isPresent());
    }
}
