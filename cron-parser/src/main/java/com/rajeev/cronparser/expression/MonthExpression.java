package com.rajeev.cronparser.expression;

public class MonthExpression extends BaseExpression{
    public MonthExpression(String expr) {
        super(expr);
        this.minm = 1;
        this.maxm = 12;
    }
}
