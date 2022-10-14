package com.rajeev.cronparser.parser;

import com.rajeev.cronparser.expression.BaseExpression;

import java.util.ArrayList;
import java.util.List;

public class ExactParser extends BaseParser{
    public ExactParser(BaseExpression expr) {
        super(expr);
    }

    @Override
    public List<Integer> generateAllPossibileExpr() {
        Integer val = Integer.valueOf(this.expr.getExpr());

        if(val > this.expr.getMaxm())
            throw new RuntimeException("The value of expr is more than the maximum allowed.");

        if(val < this.expr.getMinm())
            throw new RuntimeException("The value of expr is less than the minimum value allowed");

        return new ArrayList<Integer>() {{add(val);}};
    }
}
