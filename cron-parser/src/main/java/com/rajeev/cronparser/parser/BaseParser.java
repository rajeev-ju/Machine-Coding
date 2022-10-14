package com.rajeev.cronparser.parser;


import com.rajeev.cronparser.expression.BaseExpression;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.lang.reflect.InvocationTargetException;

public abstract class BaseParser {

    protected static final String ALL_SPECIFIER = "*";
    protected static final String LIST_SPECIFIER = ",";
    protected static final String RANGE_SPECIFIER = "-";
    protected static final String EXACT_SPECIFIER = "e";
    protected static final String STEP_SPECIFIER = "/";
    protected BaseExpression expr;

    private static final Map<String, Class> map = new HashMap<String, Class>(){{
        put(ALL_SPECIFIER, AnyParser.class);
        put(LIST_SPECIFIER, ListParser.class);
        put(RANGE_SPECIFIER, RangeParser.class);
        put(EXACT_SPECIFIER, ExactParser.class);
        put(STEP_SPECIFIER, StepParser.class);
    }};

    public BaseParser(BaseExpression expr){
        this.expr = expr;
    }

    public static BaseParser get(BaseExpression expr) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String parser = null;

        if(expr.getExpr().equals(ALL_SPECIFIER))
            parser = ALL_SPECIFIER;
        if(expr.getExpr().matches(".*,.*"))
            parser = LIST_SPECIFIER;
        if(expr.getExpr().matches("[0-9]+-[0-9]+"))
            parser = RANGE_SPECIFIER;
        if(expr.getExpr().matches("^[0-9]+$"))
            parser = EXACT_SPECIFIER;
        if(expr.getExpr().matches(".*\\/.*"))
            parser = STEP_SPECIFIER;

        if(parser == null)
            throw new RuntimeException("Invalid cron expression : " + expr.getExpr());

        return (BaseParser) map.get(parser).getDeclaredConstructor(BaseExpression.class).newInstance(expr);
    }

    public abstract List<Integer> generateAllPossibileExpr();
}
