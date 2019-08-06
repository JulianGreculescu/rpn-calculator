package au.com.gritmed.rpn.input;

import org.apache.commons.lang3.StringUtils;

import java.util.Optional;
import java.util.Scanner;

public class KeyboardReader implements LineReader {
    private final Scanner keyboard;

    public KeyboardReader() {
        keyboard = new Scanner(System.in);
    }

    @Override
    public Optional<String> readLine() {
        String line = keyboard.nextLine();

        return Optional.ofNullable(StringUtils.isBlank(line) ? null : line);
    }
}
