package au.com.gritmed.rpn.calculator;

import au.com.gritmed.rpn.domain.Operand;

import java.math.BigDecimal;

public final class SampleDataSource {
    static final int TEN = 10;
    static final Operand ONE = new Operand(new BigDecimal(1));
    static final Operand TWO = new Operand(new BigDecimal(2));
    static final Operand THREE = new Operand(new BigDecimal(3));
    static final Operand FOUR = new Operand(new BigDecimal(4));

    static Cache oneOperansCache() {
        Cache cache = new Cache();
        cache.getOperands().push(ONE);

        return cache;
    }

    static Cache twoOperandsCache() {
        Cache cache = oneOperansCache();
        cache.getOperands().push(TWO);

        return cache;
    }

    static Cache threeOperandsCache() {
        Cache cache = twoOperandsCache();
        cache.getOperands().push(THREE);

        return cache;
    }
}
