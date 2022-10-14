package com.rajeev.cronparser.parser;

import com.rajeev.cronparser.expression.BaseExpression;
import com.rajeev.cronparser.expression.DayExpression;
import com.rajeev.cronparser.expression.MonthExpression;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ExactParserTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testParserPossibilitiesForExactDay() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new DayExpression("1");
        Assert.assertEquals(expr.parse(), new ArrayList<Integer>(){{add(1);}});
    }

    @Test
    public void testParserWithExceptionOfMinimumForExactDay() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new MonthExpression("0");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("The value of expr is less than the minimum value allowed");
        expr.parse();
    }

    @Test
    public void testParserWithExceptionForExactDay() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new DayExpression("59");
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("The value of expr is more than the maximum allowed");
        expr.parse();
    }
}
