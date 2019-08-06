package au.com.gritmed.rpn.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OperatorTest {
    @ParameterizedTest
    @ValueSource(strings = {"invalid", "abs"})
    void shouldNotReconiseInvalidOrUnsuportedOperators(String value) {
        Throwable thrown = assertThrows(IllegalArgumentException.class, () -> Operation.ofSymbol(value));
        assertEquals("Not a valid operator. Acceptable kyes insensitive values are: +, -, *, /, sqrt, undo, clear", thrown.getMessage());
    }
}