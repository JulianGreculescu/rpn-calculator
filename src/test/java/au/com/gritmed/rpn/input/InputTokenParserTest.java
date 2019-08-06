package au.com.gritmed.rpn.input;

import au.com.gritmed.rpn.domain.Operand;
import au.com.gritmed.rpn.domain.Operator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.math.BigDecimal;
import java.util.Optional;

import static au.com.gritmed.rpn.domain.Operation.DIVIDE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputTokenParserTest {
    private static final int TEN = 10;

    @Test
    void shouldBeAbleToParseNumbers() {
        Optional<InputToken> oInputToken = InputTokenParser.parse("12.345", TEN);
        assertTrue(oInputToken.isPresent());
        assertTrue(oInputToken.get() instanceof Operand);
        assertEquals(new BigDecimal("12.345"), ((Operand) oInputToken.get()).getValue());
    }

    void shouldBeAbleToParseOperators() {
        Optional<InputToken> oInputToken = InputTokenParser.parse("/", TEN);
        assertTrue(oInputToken.isPresent());
        assertTrue(oInputToken.get() instanceof Operator);
        assertEquals(DIVIDE, oInputToken.get());
    }

    @ParameterizedTest
    @ValueSource(strings = {"plus", "/123"})
    void shouldReturnNothingWhernNeitherANumberrNorAnOperator(String token) {
        Optional<InputToken> oInputToken = InputTokenParser.parse(token, TEN);
        assertFalse(oInputToken.isPresent());
    }
}