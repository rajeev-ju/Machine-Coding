package com.rajeev.cronparser.expression;

public class WeekDayExpression extends BaseExpression{
    public WeekDayExpression(String expr) {
        super(expr);
        this.minm = 0;
        this.maxm = 6;
    }
}
