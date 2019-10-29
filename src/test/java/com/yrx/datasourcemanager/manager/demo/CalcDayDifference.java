package com.yrx.datasourcemanager.manager.demo;

import org.junit.Test;

import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by r.x on 2019/10/29.
 */
public class CalcDayDifference {

    /**
     * 计算天数差
     */
    @Test
    public void calcDayDifference() {
        LocalDate end = LocalDate.now();
        LocalDate start = LocalDate.parse("2019-10-25 22:15:10", DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        long between = ChronoUnit.DAYS.between(start, end);
        System.out.println(between);
    }


    /**
     * 计算月数差
     */
    @Test
    public void calcMonthDifference() {
        LocalDate end = LocalDate.now();
        YearMonth start = YearMonth.parse("2019-05", DateTimeFormatter.ofPattern("yyyy-MM"));
        long between = ChronoUnit.MONTHS.between(start, end);
        System.out.println(between);
    }

    /**
     * 明天
     */
    @Test
    public void tomorrow() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        Date tomorrow = calendar.getTime();
        System.out.println(tomorrow);
    }

    /**
     * 上个月
     */
    @Test
    public void lastMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, -1);
        Date lastMonth = calendar.getTime();
        System.out.println(lastMonth);
    }
}
