package com.rajeev.cronparser;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.lang.reflect.InvocationTargetException;

public class CronParserTest {
    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testCronParserExpressionWrongNubmerOfArgs() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("There must be 6 exprParts in the expression");
        new CronParser("").toString();
    }

    @Test
    public void testCronParserWithSimpleCronExpr() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Assert.assertEquals(new CronParser("*/15 10 1,2,3 * 1-3 /usr/bin/find").toString(), "minute        0 15 30 45\n"
                + "hour          10\n"
                + "day of month  1 2 3\n"
                + "month         1 2 3 4 5 6 7 8 9 10 11 12\n"
                + "day of week   1 2 3\n"
                + "command       /usr/bin/find");
    }

    @Test
    public void testCronParserWithExpressionWithListsAndYear()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Assert.assertEquals(new CronParser("*/15 0 1-5,1-15 * 1-5 2016 /usr/bin/find").toString(), "minute        0 15 30 45\n"
                + "hour          0\n"
                + "day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15\n"
                + "month         1 2 3 4 5 6 7 8 9 10 11 12\n"
                + "day of week   1 2 3 4 5\n"
                + "year          2016\n"
                + "command       /usr/bin/find");
    }

    @Test
    public void testCronParserWithExpressionWithLists()
            throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        Assert.assertEquals(new CronParser("*/15 0 1-5,1-15 * 1-5 /usr/bin/find").toString(), "minute        0 15 30 45\n"
                + "hour          0\n"
                + "day of month  1 2 3 4 5 6 7 8 9 10 11 12 13 14 15\n"
                + "month         1 2 3 4 5 6 7 8 9 10 11 12\n"
                + "day of week   1 2 3 4 5\n"
                + "command       /usr/bin/find");
    }


    @Test
    public void testCronParserExpressionWithInvalidStep() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        exceptionRule.expect(RuntimeException.class);
        exceptionRule.expectMessage("Step size is more than maximum value");
        new CronParser("*/105 0 1,5 * 1-5 /usr/bin/find").toString();
    }
}
