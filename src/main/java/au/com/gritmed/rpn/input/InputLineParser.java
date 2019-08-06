package au.com.gritmed.rpn.input;

import au.com.gritmed.rpn.domain.InputLineParsingResult;

import java.util.Optional;

public class InputLineParser {
    public InputLineParsingResult parseInputLine(String inputLine) {
        InputLineParsingResult result = new InputLineParsingResult();
        StringBuilder token = new StringBuilder();
        int idx = 0;
        for (char chr : inputLine.toCharArray()) {
            ++idx;
            boolean tokenStarted = !Character.isWhitespace(chr);
            if (tokenStarted) {
                token.append(chr);
            } else if (token.toString().trim().length() > 0) {
                addInputToken(result, token.toString(), idx);
                if (result.getError().isPresent()) {
                    break;
                }
                token.setLength(0);
            }
        }
        if (token.toString().trim().length() > 0) {
            addInputToken(result, token.toString(), idx + 1);
        }

       return result;
    }

    private void addInputToken(InputLineParsingResult result, String token, int endPosition) {
        Optional<InputToken> inputToken = InputTokenParser.parse(token, endPosition - token.length());
        if (inputToken.isPresent()) {
            result.addInputToken(inputToken.get());
        } else {
            result.setError("Invalid token: " + token);
        }
    }
}
