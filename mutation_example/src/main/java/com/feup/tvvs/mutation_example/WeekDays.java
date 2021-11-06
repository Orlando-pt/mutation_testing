package com.feup.tvvs.mutation_example;

import java.util.HashMap;
import java.util.Map;

public enum WeekDays {
    SUNDAY(0), MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6);

    private int value;
    private static Map map = new HashMap<>();

    WeekDays(int value) {
        this.value = value;
    }

    static {
        for (WeekDays weekDay : WeekDays.values()) {
            map.put(weekDay.value, weekDay);
        }
    }

    public static WeekDays valueOf(int weekDay) {
        return (WeekDays) map.get(weekDay);
    }

    public int getValue() {
        return value;
    }
}
