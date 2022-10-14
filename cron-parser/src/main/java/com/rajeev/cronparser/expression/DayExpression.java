package com.rajeev.cronparser.expression;

import java.time.YearMonth;

public class DayExpression extends BaseExpression{
    public DayExpression(String expr) {
        super(expr);
        this.minm = 1;
        this.maxm = YearMonth.now().lengthOfMonth();
    }
}
