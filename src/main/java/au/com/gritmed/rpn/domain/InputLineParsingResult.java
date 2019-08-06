package au.com.gritmed.rpn.domain;

import au.com.gritmed.rpn.input.InputToken;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InputLineParsingResult {
    @Getter
    private final List<InputToken> inputTokens = new ArrayList<>();
    @Setter
    private String error;

    public void addInputToken(InputToken inputToken) {
        inputTokens.add(inputToken);
    }

    public Optional<String> getError() {
        return Optional.ofNullable(error);
    }
}
