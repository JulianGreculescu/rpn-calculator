
package au.com.gritmed.rpn.domain;

import au.com.gritmed.rpn.input.InputToken;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Operator implements InputToken {
    private final Operation operation;
    private final int position;
}
