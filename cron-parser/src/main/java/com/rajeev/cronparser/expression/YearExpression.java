package com.rajeev.cronparser.expression;

public class YearExpression extends BaseExpression{
    public YearExpression(String expr) {
        super(expr);
        this.minm = 2000;
        this.maxm = 2099;
    }
}
