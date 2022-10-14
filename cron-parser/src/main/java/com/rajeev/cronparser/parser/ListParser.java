package com.rajeev.cronparser.parser;

import com.rajeev.cronparser.expression.BaseExpression;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListParser extends BaseParser{
    public ListParser(BaseExpression expr) {
        super(expr);
    }

    @Override
    public List<Integer> generateAllPossibileExpr() {
        List<String> exprList = Arrays.asList(this.expr.getExpr().split(","));

        if(exprList.size() < 2)
            throw new RuntimeException("List does not have valid expression : " + this.expr.getExpr());

        return exprList.stream().flatMap(e ->{
            try{
                return BaseParser.get(new BaseExpression(e){
                    @Override
                    public int getMinm(){
                        return expr.getMinm();
                    }

                    @Override
                    public int getMaxm(){
                        return expr.getMaxm();
                    }

                    @Override
                    public String getExpr(){
                        return e;
                    }
                }).generateAllPossibileExpr().stream();
            } catch (Exception ex){
                throw new RuntimeException(ex.getMessage());
            }
        }).distinct().sorted().collect(Collectors.toList());

    }
}
