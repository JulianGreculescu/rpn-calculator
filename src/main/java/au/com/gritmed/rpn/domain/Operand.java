package au.com.gritmed.rpn.domain;

import au.com.gritmed.rpn.input.InputToken;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@EqualsAndHashCode
public class Operand implements InputToken {
    private static final int MAX_DISPLAY_SCALE = 10;
    private static final String ZERO_EXP = "0E-" + MAX_DISPLAY_SCALE;

    private final BigDecimal value;

    public Operand(BigDecimal value) {
        if (value == null) {
            throw new IllegalArgumentException("Operand cannot be null");
        }
        this.value = value;
    }

    @Override
    public String toString() {
        String str = value.setScale(MAX_DISPLAY_SCALE, RoundingMode.FLOOR).toString();
        if (ZERO_EXP.equalsIgnoreCase(str)) {
            str = "0";
        }
        return str.contains(".") ? str.replaceAll("0*$", "").replaceAll("\\.$", "") : str;
    }
}
