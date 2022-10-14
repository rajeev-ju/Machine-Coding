package com.rajeev.cronparser.parser;

import com.rajeev.cronparser.expression.BaseExpression;
import com.rajeev.cronparser.expression.MinuteExpression;
import com.rajeev.cronparser.expression.WeekDayExpression;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ListParserTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testListPossibilities() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new MinuteExpression("1,5");
        Assert.assertEquals(expr.parse(), new ArrayList<Integer>(){{add(1); add(5);}});
    }

    @Test
    public void testListWithInvalidElement() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new MinuteExpression("a,15");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Invalid cron expression : a");
        expr.parse();
    }

    @Test
    public void testListWithMoreThanMaximumValue() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new WeekDayExpression("1,8");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("The value of expr is more than the maximum allowed");
        expr.parse();
    }

    @Test
    public void testListWithMissingValue() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new WeekDayExpression("1,");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("List does not have valid expression : 1,");
        expr.parse();
    }

    @Test
    public void testListWithValidComplexValues()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new WeekDayExpression("1-2,5-6");
        Assert.assertEquals(expr.parse(), new ArrayList<Integer>(){{add(1); add(2); add(5); add(6);}});
    }

    @Test
    public void testListWithValidComplexValues2()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new WeekDayExpression("1-4,3-6");
        Assert.assertEquals(expr.parse(), new ArrayList<Integer>(){{add(1); add(2); add(3); add(4); add(5); add(6);}});
    }
}
