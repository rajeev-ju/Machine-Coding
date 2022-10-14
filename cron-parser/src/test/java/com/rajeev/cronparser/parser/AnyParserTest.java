package com.rajeev.cronparser.parser;

import com.rajeev.cronparser.expression.BaseExpression;
import com.rajeev.cronparser.expression.DayExpression;
import com.rajeev.cronparser.expression.WeekDayExpression;
import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class AnyParserTest {
    @Test
    public void testAnyParserForAllDays() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression d = new DayExpression(BaseParser.ALL_SPECIFIER);
        Assert.assertEquals(d.parse(), new ArrayList<Integer>(){{add(1); add(2); add(3); add(4); add(5); add(6);
            add(7); add(8); add(9); add(10); add(11); add(12); add(13); add(14); add(15); add(16); add(17); add(18); add(19);
            add(20); add(21); add(22); add(23); add(24); add(25); add(26); add(27); add(28); add(29); add(30); add(31);}});
    }

    @Test
    public void testAnyParserForAllWeekDays() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        BaseExpression expr = new WeekDayExpression(BaseParser.ALL_SPECIFIER);
        Assert.assertEquals(expr.parse(), new ArrayList<Integer>(){{add(0); add(1); add(2); add(3); add(4); add(5); add(6);}});
    }
}
