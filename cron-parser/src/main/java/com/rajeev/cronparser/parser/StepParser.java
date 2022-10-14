package com.rajeev.cronparser.parser;

import com.rajeev.cronparser.expression.BaseExpression;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class StepParser extends BaseParser{
    public StepParser(BaseExpression expr) {
        super(expr);
    }

    @Override
    public List<Integer> generateAllPossibileExpr() throws RuntimeException{
        String[] steps = this.expr.getExpr().split("/");

        if (steps.length != 2)
            throw new RuntimeException("Step does not have valid expression : " + this.expr.getExpr());

        if (steps[0].equals("*"))
            steps[0] = String.valueOf(this.expr.getMinm());

        List<Integer> res = new ArrayList<>();
        Integer left_range = null, right_range = null, step = null;

        if(this.expr.getExpr().contains(",") && !this.expr.getExpr().contains("-"))
            throw new RuntimeException("Step does not have valid expression : " + this.expr.getExpr());
        else if(this.expr.getExpr().contains(",") && this.expr.getExpr().contains("-")){
            try {
                List<String> temp = Arrays.asList(this.expr.getExpr().split(","));
                for (String str : temp) {
                    if (!str.contains("-"))
                        res.add(Integer.valueOf(str));
                    else {
                        left_range = Integer.valueOf(str.split("-")[0]);
                        right_range = Integer.valueOf(str.split("/")[0].split("-")[1]);
                        step = Integer.valueOf(str.split("/")[1]);
                    }
                }
            }catch (Exception ex){
                throw new RuntimeException("Step does not have valid expression : " + this.expr.getExpr());
            }
        }
        else if (steps[0].contains("-")) {
            try {
                left_range = Integer.valueOf(steps[0].split("-")[0]);
                right_range = Integer.valueOf(steps[0].split("-")[1]);
                step = Integer.valueOf(steps[1]);
            }catch (Exception ex) {
                throw new RuntimeException("Step does not have valid expression : " + this.expr.getExpr());
            }
        }
        else {
            try {
                left_range = Integer.valueOf(steps[0]);
                right_range = this.expr.getMaxm();
                step = Integer.valueOf(steps[1]);
            } catch (Exception ex) {
                throw new RuntimeException("Step does not have valid expression : " + this.expr.getExpr());
            }
        }

        if (step > this.expr.getMaxm())
            throw new RuntimeException("Step size is more than maximum value");

        if (left_range > this.expr.getMaxm()) {
            throw new RuntimeException("Step start is more than maximum value");
        }
        Integer finalStep = step;
        res.addAll(IntStream.iterate(left_range, n -> n + finalStep).limit((right_range - left_range)/ step + 1).boxed().collect(Collectors.toList()));

        return res;
    }
}
