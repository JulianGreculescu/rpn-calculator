package au.com.gritmed.rpn.domain;

import au.com.gritmed.rpn.calculator.AddOperation;
import au.com.gritmed.rpn.calculator.ClearOperation;
import au.com.gritmed.rpn.calculator.DivideOperation;
import au.com.gritmed.rpn.calculator.MultiplyOperation;
import au.com.gritmed.rpn.calculator.OperationExecutor;
import au.com.gritmed.rpn.calculator.SqrtOperation;
import au.com.gritmed.rpn.calculator.SubstractOperation;
import au.com.gritmed.rpn.calculator.UndoOperation;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toMap;

@AllArgsConstructor
@Getter
public enum Operation {
    ADD("+", new AddOperation()),
    SUBSTRACT("-", new SubstractOperation()),
    MULTIPLY("*", new MultiplyOperation()),
    DIVIDE("/", new DivideOperation()),
    SQRT("sqrt", new SqrtOperation()),
    UNDO("undo", new UndoOperation()),
    CLEAR("clear", new ClearOperation());

    private static final Map<String, Operation> OPERATORS =
            Arrays.stream(values()).collect(toMap(v -> v.symbol, v -> v));
    private static final String SYMBOLS = Arrays.stream(values()).map(o -> o.symbol).collect(joining(", "));

    private final String symbol;
    private final OperationExecutor executor;

    public static Operation ofSymbol(String symbol) {
        String key = symbol.toLowerCase(Locale.getDefault());
        if (OPERATORS.containsKey(key)) {
            return OPERATORS.get(key);
        }
        throw new IllegalArgumentException("Not a valid operator. Acceptable kyes insensitive values are: " + SYMBOLS);
    }
}
