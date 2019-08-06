package au.com.gritmed.rpn.input;

import au.com.gritmed.rpn.domain.InputLineParsingResult;
import au.com.gritmed.rpn.domain.Operand;
import au.com.gritmed.rpn.domain.Operator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static au.com.gritmed.rpn.domain.Operation.ADD;
import static au.com.gritmed.rpn.domain.Operation.MULTIPLY;
import static au.com.gritmed.rpn.domain.Operation.SQRT;
import static au.com.gritmed.rpn.domain.Operation.SUBSTRACT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class InputLineParserTest {
    private static final Operand TWO = new Operand(new BigDecimal(2));
    private static final Operand FIVE = new Operand(new BigDecimal(5));

    private InputLineParser parser;

    @BeforeEach
    void setUp() {
        parser = new InputLineParser();
    }

    @Test
    void shouldBeAbleToParseOperandsOnlyLine() {
        InputLineParsingResult result = parser.parseInputLine("5 2");

        assertFalse(result.getError().isPresent());
        assertEquals(2, result.getInputTokens().size());
        assertTrue(result.getInputTokens().get(0) instanceof Operand);
        assertTrue(result.getInputTokens().get(1) instanceof Operand);
        assertEquals(FIVE, result.getInputTokens().get(0));
        assertEquals(TWO, result.getInputTokens().get(1));
    }

    @Test
    void shouldBeAbleToParseOperantorsOnlyLine() {
        InputLineParsingResult result = parser.parseInputLine("* + -    sqrt");

        assertFalse(result.getError().isPresent());
        assertEquals(4, result.getInputTokens().size());
        assertTrue(result.getInputTokens().get(0) instanceof Operator);
        assertTrue(result.getInputTokens().get(1) instanceof Operator);
        assertTrue(result.getInputTokens().get(2) instanceof Operator);
        assertTrue(result.getInputTokens().get(3) instanceof Operator);
        assertEquals(MULTIPLY, ((Operator) result.getInputTokens().get(0)).getOperation());
        assertEquals(1, ((Operator) result.getInputTokens().get(0)).getPosition());
        assertEquals(ADD, ((Operator) result.getInputTokens().get(1)).getOperation());
        assertEquals(3, ((Operator) result.getInputTokens().get(1)).getPosition());
        assertEquals(SUBSTRACT, ((Operator) result.getInputTokens().get(2)).getOperation());
        assertEquals(5, ((Operator) result.getInputTokens().get(2)).getPosition());
        assertEquals(SQRT, ((Operator) result.getInputTokens().get(3)).getOperation());
        assertEquals(10, ((Operator) result.getInputTokens().get(3)).getPosition());
    }

    @Test
    void shouldBeAbleToParseMixureOfOperandsAndOperantorsLine() {
        InputLineParsingResult result = parser.parseInputLine("2 5 * sqrt");

        assertFalse(result.getError().isPresent());
        assertEquals(4, result.getInputTokens().size());
        assertTrue(result.getInputTokens().get(0) instanceof Operand);
        assertTrue(result.getInputTokens().get(1) instanceof Operand);
        assertTrue(result.getInputTokens().get(2) instanceof Operator);
        assertTrue(result.getInputTokens().get(3) instanceof Operator);
        assertEquals(TWO, result.getInputTokens().get(0));
        assertEquals(FIVE, result.getInputTokens().get(1));
        assertEquals(MULTIPLY, ((Operator) result.getInputTokens().get(2)).getOperation());
        assertEquals(5, ((Operator) result.getInputTokens().get(2)).getPosition());
        assertEquals(SQRT, ((Operator) result.getInputTokens().get(3)).getOperation());
        assertEquals(7, ((Operator) result.getInputTokens().get(3)).getPosition());
    }

    @Test
    void shouldBeAblleToDetectInputErrors() {
        InputLineParsingResult result = parser.parseInputLine("2 5 x sqrt");

        assertTrue(result.getError().isPresent());
        assertEquals("Invalid token: x", result.getError().get());
    }
}
