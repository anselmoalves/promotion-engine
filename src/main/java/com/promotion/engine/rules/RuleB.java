package com.promotion.engine.rules;

import com.promotion.engine.Item;
import com.promotion.engine.Product;
import com.promotion.engine.SKU;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Map;

@NoArgsConstructor
public class RuleB implements Rule {

    private static final BigDecimal UNIT_PRICE = BigDecimal.valueOf(30);
    private static final BigDecimal BUNDLE_QUANTITY = BigDecimal.valueOf(2);
    private static final BigDecimal BUNDLE_PRICE = BigDecimal.valueOf(45);

    @Override
    public BigDecimal apply(Map<SKU, Item> items) {
        BigDecimal quantity = items.getOrDefault(SKU.B, Item.of(SKU.B).withQuantity(BigDecimal.ZERO)).getQuantity();
        BigDecimal bundledQuantity = (quantity.subtract(quantity.remainder(BUNDLE_QUANTITY)))
                .divide(BUNDLE_QUANTITY, RoundingMode.DOWN);
        quantity = quantity.remainder(BUNDLE_QUANTITY);

        return bundledQuantity.multiply(BUNDLE_PRICE).add(quantity.multiply(UNIT_PRICE));
    }
}
