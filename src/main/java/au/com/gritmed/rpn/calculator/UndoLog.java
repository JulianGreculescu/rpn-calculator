package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.UndoLogEntry;

import java.util.EmptyStackException;
import java.util.Optional;
import java.util.Stack;

public class UndoLog {
    private final Stack<UndoLogEntry> undoStack = new Stack<>();

    public void push(UndoLogEntry undoLogEntry) {
        switch (undoLogEntry.getOperation()) {
            case UNDO:
                // Undo is not undoable but rather repeatable so ignore
                break;
            case CLEAR:
                // Only undo a clear after it actually cleared something
                // No point in undoing an empty CLEAN as it creates confusion
                // with nothing happening on the calculator display. Adding
                // support for a undoing a CLEAN is arguable itself.
                if (undoLogEntry.getOperands().length > 0) {
                    undoStack.push(undoLogEntry);
                }
                break;
            default:
                undoStack.push(undoLogEntry);
        }
    }

    public Optional<UndoLogEntry> pop() {
        try {
            return Optional.of(undoStack.pop());
        } catch (EmptyStackException e) {
            return Optional.empty();
        }
    }
}
