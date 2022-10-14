package com.rajeev.cronparser;

import com.rajeev.cronparser.expression.*;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class CronParser {
    String expression;
    HashMap<String, List> exprMap;
    String cmd;

    static List<String> displayOrder = new ArrayList<String>(){{add("minute");
        add("hour");
        add("day");
        add("month");
        add("weekday");
        add("year");
    }};

    static Map<String, String> displayString = new HashMap<String, String>() {{
        put("day", "day of month");
        put("weekday", "day of week");
    }};

    public CronParser(String expression) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.expression = expression;
        this.exprMap = new HashMap<>();
        this.parse();
    }

    public static void main(String[] args) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (args.length != 1)
            throw new RuntimeException("There must be 1 argument : " + String.join(";", args));

        CronParser parsedCronExpr = new CronParser(args[0]);
        System.out.println(parsedCronExpr.toString());
    }

    /**
     * @param exprParts
     * @param startingIndex
     * @return command which is a part of cron expression
     */
    private String extractCommand(String[] exprParts, int startingIndex) {
        String arguments = "";
        for (int i = startingIndex; i < exprParts.length; i++) {
            arguments += " " + exprParts[i];
        }
        return arguments;
    }

    /**
     * This function is used to parse the expression on the basis of each part of the expression such as minute, hour, day, etc.
     * @return CronParser object which contains the parsed expression
     * @throws NoSuchMethodException
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws InvocationTargetException
     */
    private CronParser parse() throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {
        System.out.println("Cron Expression entered : " + this.expression);
        String[] exprParts = this.expression.split("\\s+");

        if (exprParts.length < 6)
            throw new RuntimeException("There must be 6 exprParts in the expression");

        int i = 0;
        this.exprMap.put("minute", new MinuteExpression(exprParts[i++]).parse());
        this.exprMap.put("hour", new HourExpression(exprParts[i++]).parse());
        this.exprMap.put("day", new DayExpression(exprParts[i++]).parse());
        this.exprMap.put("month", new MonthExpression(exprParts[i++]).parse());
        this.exprMap.put("weekday", new WeekDayExpression(exprParts[i++]).parse());

        try {
            this.exprMap.put("year", new YearExpression(exprParts[i++]).parse());
        } catch (Exception e) {
            i--;
        }
        this.cmd = exprParts[i] + extractCommand(exprParts, i + 1);
        return this;
    }

    /**
     * @return It returns a string in the required format after parsing the cron expression
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (String section : displayOrder) {
            if (this.exprMap.get(section) == null) {
                continue;
            }
            String displayString = this.displayString.getOrDefault(section, section);
            sb.append(String.format(displayString + new String(new char[14 - displayString.length()]).replace("\0", " ") + "%s",
                    this.exprMap.get(section).stream().map(i -> i.toString()).collect(
                            Collectors.joining(" "))));
            sb.append(System.getProperty("line.separator"));
        }
        sb.append(String.format("command       %s", this.cmd));
        return sb.toString();
    }
}
