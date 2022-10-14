package com.rajeev.cronparser.parser;

import com.rajeev.cronparser.expression.BaseExpression;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class RangeParser extends BaseParser{
    public RangeParser(BaseExpression expr) {
        super(expr);
    }

    @Override
    public List<Integer> generateAllPossibileExpr() {
        List<Integer> rangeLimits = Arrays.asList(this.expr.getExpr().split("-")).stream()
                .map(Integer::valueOf).collect(
                        Collectors.toList());

        if (rangeLimits.size() != 2)
            throw new RuntimeException("Range does not have valid expression : " + this.expr.getExpr());

        if (rangeLimits.get(1) < rangeLimits.get(0))
            throw new RuntimeException("Range minimum/maximum are in wrong order. maximum should be : " + rangeLimits.get(0) + " and minimum should be : " + rangeLimits.get(1));

        if (rangeLimits.get(0) < this.expr.getMinm())
            throw new RuntimeException("Range minimum is not valid. Given : " + rangeLimits.get(0) + " Min allowed : " + this.expr.getMinm());

        if (rangeLimits.get(0) > this.expr.getMaxm())
            throw new RuntimeException("Range minimum is not valid. Given : " + rangeLimits.get(0) + " Max allowed : " + this.expr.getMaxm());

        if (rangeLimits.get(1) > this.expr.getMaxm())
            throw new RuntimeException("Range maximum is not valid. Given : " + rangeLimits.get(1) + " Max allowed : " + this.expr.getMaxm());

        return Arrays.stream(IntStream.range(rangeLimits.get(0), rangeLimits.get(1) + 1).toArray())
                .boxed().collect(
                        Collectors.toList());

    }
}
