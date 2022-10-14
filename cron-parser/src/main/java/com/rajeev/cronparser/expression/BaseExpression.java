package com.rajeev.cronparser.expression;

import com.rajeev.cronparser.parser.BaseParser;
import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Getter
@Setter
public abstract class BaseExpression<T> {
    int minm;
    int maxm;
    String expr;
    List<T> allPossibleExpr;

    public BaseExpression(String expr){
        this.expr = expr;
    }

    public List<T> parse() throws RuntimeException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.allPossibleExpr = (List<T>) BaseParser.get(this).generateAllPossibileExpr();
        return this.allPossibleExpr;
    }
}
