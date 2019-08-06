package au.com.gritmed.rpn.calculator;

import lombok.Getter;

@Getter
public class Cache {
    private final OperandsStack operands;
    private final UndoLog undoLog;

    public Cache() {
        operands = new OperandsStack();
        undoLog = new UndoLog();
    }
}
