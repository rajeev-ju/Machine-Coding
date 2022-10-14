package com.rajeev.cronparser.expression;

public class HourExpression extends BaseExpression{
    public HourExpression(String expr) {
        super(expr);
        this.minm = 0;
        this.maxm = 23;
    }
}
