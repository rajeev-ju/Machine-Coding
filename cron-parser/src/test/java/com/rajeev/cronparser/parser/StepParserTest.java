package com.rajeev.cronparser.parser;

import com.rajeev.cronparser.expression.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class StepParserTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testStepParserWithAllSpecifier()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new MinuteExpression("*/15");
        Assert.assertEquals(expr.parse(), new ArrayList<Integer>(){{add(0); add(15); add(30); add(45);}});
    }

    @Test
    public void testStepParserWithWrongStepValue() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new MinuteExpression("*/60");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Step size is more than maximum value");
        expr.parse();
    }

    @Test
    public void testStepParserWithInvalidExpr() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new MinuteExpression("*/");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Step does not have valid expression : */");
        expr.parse();
    }

    @Test
    public void testStepParserWithWrongStepStart() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new MinuteExpression("60/15");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Step start is more than maximum value");
        expr.parse();
    }

    @Test
    public void testStepParserWithInvalidStepStart() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new MinuteExpression("a/15");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Step does not have valid expression : a/15");
        expr.parse();
    }


    @Test
    public void testStepParserWithWrongStepValueForYear() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new YearExpression("2096/2");

        Assert.assertEquals(expr.parse(), new ArrayList<Integer>(){{add(2096); add(2098);}});
    }
}
