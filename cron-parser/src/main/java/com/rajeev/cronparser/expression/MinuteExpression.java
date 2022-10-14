package com.rajeev.cronparser.expression;

public class MinuteExpression extends BaseExpression{
    public MinuteExpression(String expr) {
        super(expr);
        this.minm = 0;
        this.maxm = 59;
    }
}
