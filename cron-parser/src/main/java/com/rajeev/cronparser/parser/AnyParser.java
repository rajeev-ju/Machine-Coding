package com.rajeev.cronparser.parser;

import com.rajeev.cronparser.expression.BaseExpression;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class AnyParser extends BaseParser{
    public AnyParser(BaseExpression expr) {
        super(expr);
    }

    @Override
    public List<Integer> generateAllPossibileExpr() {
        return Arrays.stream(IntStream.range(this.expr.getMinm(), this.expr.getMaxm() + 1).toArray()).boxed().collect(
                Collectors.toList());
    }
}
