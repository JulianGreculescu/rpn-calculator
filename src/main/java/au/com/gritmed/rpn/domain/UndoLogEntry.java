package au.com.gritmed.rpn.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@Getter
@EqualsAndHashCode
public class UndoLogEntry {
    private final Operation operation;
    private final Operand[] operands;
}
