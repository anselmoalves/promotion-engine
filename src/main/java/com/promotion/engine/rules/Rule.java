package com.promotion.engine.rules;

import com.promotion.engine.Item;
import com.promotion.engine.SKU;

import java.math.BigDecimal;
import java.util.Map;
import java.util.function.Function;

public interface Rule extends Function<Map<SKU, Item>, BigDecimal> {

    String getName();
}
