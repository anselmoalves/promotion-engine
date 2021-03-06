package com.promotion.engine.rules;

import com.promotion.engine.Item;
import com.promotion.engine.SKU;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Map;

@NoArgsConstructor
public class RuleCD implements Rule {

    private static final String RULE_NAME = "RULE_CD";
    private static final BigDecimal UNIT_PRICE_C = BigDecimal.valueOf(20);
    private static final BigDecimal UNIT_PRICE_D = BigDecimal.valueOf(15);
    private static final BigDecimal FIXED_CD_PRICE = BigDecimal.valueOf(30);

    @Override
    public BigDecimal apply(Map<SKU, Item> items) {
        BigDecimal quantityC = items.getOrDefault(SKU.C, Item.of(SKU.C).withQuantity(BigDecimal.ZERO)).getQuantity();
        BigDecimal quantityD = items.getOrDefault(SKU.D, Item.of(SKU.D).withQuantity(BigDecimal.ZERO)).getQuantity();

        return quantityC.min(quantityD).multiply(FIXED_CD_PRICE)
                .add(quantityC.subtract(quantityC.min(quantityD)).multiply(UNIT_PRICE_C))
                .add(quantityD.subtract(quantityC.min(quantityD)).multiply(UNIT_PRICE_D));
    }

    @Override
    public String getName() {
        return RULE_NAME;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Rule rule = (Rule) o;

        return getName().equals(rule.getName());
    }

    @Override
    public int hashCode() {
        return RULE_NAME.hashCode();
    }
}
