package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.InputLineParsingResult;
import au.com.gritmed.rpn.domain.Operand;
import au.com.gritmed.rpn.domain.Operator;
import au.com.gritmed.rpn.input.InputLineParser;
import au.com.gritmed.rpn.input.InputToken;
import au.com.gritmed.rpn.output.Display;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
public class LineProcessor {
    private final InputLineParser inputLineParser;
    private final Display display;

    public void processLine(String line, Cache cache) {
        InputLineParsingResult result = inputLineParser.parseInputLine(line);
        Optional<String> oError = result.getError();
        if (oError.isPresent()) {
            display.show(oError.get());
        } else {
            performCalculation(result.getInputTokens(), cache);
        }
        display.show(cache.getOperands().toString());
    }

    private void performCalculation(List<InputToken> inputTokens, Cache cache) {
        for (InputToken token : inputTokens) {
            if (token instanceof Operand) {
                cache.getOperands().push((Operand) token);
            } else {
                Operator operator = (Operator) token;
                Optional<String> oError = operator.getOperation().getExecutor().updateCache(cache, operator.getPosition());
                if (oError.isPresent()) {
                    oError.ifPresent(error -> display.show(error));
                    break;
                }
            }
        }
     }
}
