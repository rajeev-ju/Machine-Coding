package com.rajeev.cronparser.parser;

import com.rajeev.cronparser.expression.BaseExpression;
import com.rajeev.cronparser.expression.DayExpression;
import com.rajeev.cronparser.expression.MonthExpression;
import com.rajeev.cronparser.expression.WeekDayExpression;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class RangeParserTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testRangeParserForGivenRange()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new DayExpression("1-5");
        Assert.assertEquals(expr.parse(), new ArrayList<Integer>(){{add(1); add(2); add(3); add(4); add(5);}});
    }

    @Test
    public void testRangeParserWithInvalidRange() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new MonthExpression("6-0");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Range minimum/maximum are in wrong order. maximum should be : 6 and minimum should be : 0");
        expr.parse();
    }

    @Test
    public void testRangeParserWithMissingRangeValue() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new WeekDayExpression("1-");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Invalid cron expression : 1-");
        expr.parse();
    }

    @Test
    public void testRangeParserWithWrongMinimum() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new MonthExpression("0-6");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Range minimum is not valid. Given : 0 Min allowed : 1");
        expr.parse();
    }

    @Test
    public void testRangeParserWithWrongMaximum() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new WeekDayExpression("1-8");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Range maximum is not valid. Given : 8 Max allowed : 6");
        expr.parse();
    }

}
