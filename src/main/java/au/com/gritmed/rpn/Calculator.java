package au.com.gritmed.rpn;

import au.com.gritmed.rpn.calculator.Cache;
import au.com.gritmed.rpn.calculator.LineProcessor;
import au.com.gritmed.rpn.input.InputLineParser;
import au.com.gritmed.rpn.input.KeyboardReader;
import au.com.gritmed.rpn.input.LineReader;
import au.com.gritmed.rpn.output.ConsoleDisplay;
import lombok.AllArgsConstructor;

import java.util.Optional;

/**
 * The entry point for RPN calculator.
 * It reads one line at a time from the keyboard and processes it if valid.
 * Exit by passing a blank line.
 */
@AllArgsConstructor
public class Calculator {
    public static final void main(String[] args) {
        Calculator calculator = new Calculator(new KeyboardReader(),
                new LineProcessor(new InputLineParser(), new ConsoleDisplay()), new Cache());
        calculator.start();
    }

    private final LineReader lineReader;
    private final LineProcessor lineProcessor;
    private final Cache cache;

    public void start() {
        do {
            Optional<String> inputLine = lineReader.readLine();
            if (inputLine.isPresent()) {
                lineProcessor.processLine(inputLine.get(), cache);
            } else {
                break;
            }
        } while (true);
    }
}
