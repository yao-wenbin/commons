package io.github.yaowenbin.commons.datetime;

import io.github.yaowenbin.commons.obj.Objs;

import java.util.HashMap;

public class Times {

    public static HashMap<String, Long> timeUnitTable = new HashMap<>();

    static {
        timeUnitTable.put("s", 1000L);
        timeUnitTable.put("m", 1000L * 60);
        timeUnitTable.put("h", 1000L * 60 * 60);
        timeUnitTable.put("d", 1000L * 60 * 60 * 24);
        timeUnitTable.put("w", 1000L * 60 * 60 * 24 * 7);
        // warn: Because month and minutes have some abbreviate some let Month with M, and minutes with m.
        timeUnitTable.put("M", 1000L * 60 * 60 * 24 * 30);
        timeUnitTable.put("y", 1000L * 60 * 60 * 24 * 365);
    }

    public static Long str2Mills(String timeStr) {
        String unit = timeStr.substring(timeStr.length() - 1);
        long timeUnit = Objs.getOrDefault(timeUnitTable.get(unit), 1L);
        long time = Long.parseLong(timeStr.substring(0, timeStr.length() - 1));
        return timeUnit * time;
    }

}
