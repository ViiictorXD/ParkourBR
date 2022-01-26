package com.rukko.parkour.format;

import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class FormatTime {

    public static String format(long l) {
        return new SimpleDateFormat("dd/MM/yyyy || hh**mm")
                .format(l)
                .replace("||", "às")
                .replace("**", "h");
    }

    public static String buildString(long l) {
        Map<TimeTest, Integer> TimeTestMap = new LinkedHashMap<>();

        long leftTime = TimeUnit.MILLISECONDS.toSeconds(l + 1000);
        for (TimeTest value : TimeTest.values) {
            if (leftTime >= value.getTimeInSeconds()) {
                int durationInCurrentTime = (int) (leftTime / value.getTimeInSeconds());
                leftTime %= value.getTimeInSeconds();

                TimeTestMap.put(value, durationInCurrentTime);
            }
        }

        StringBuilder stringBuilder = new StringBuilder();
        Set<Map.Entry<TimeTest, Integer>> entries = TimeTestMap.entrySet();
        int size = entries.size();

        int index = 1;
        for (Map.Entry<TimeTest, Integer> entry : entries) {
            Integer value = entry.getValue();
            TimeTest key = entry.getKey();

            stringBuilder.append(value)
                    .append(value == 1 ? key.getSingular() : key.getPlural());

            if (index == size - 1) {
                stringBuilder.append(" e ");
            } else if (index != size) {
                stringBuilder.append(", ");
            }

            index++;
        }

        return stringBuilder.toString();
    }

    public enum TimeTest {
        YEAR(31104000, "a", "a"),
        MONTH(2592000, "m", "m"),
        DAY(86400, "d", "d"),
        HOUR(3600, "h", "h"),
        MINUTE(60, "m", "m"),
        SECOND(1, "s", "s");

        private static final TimeTest[] values = values();

        private final long timeInSeconds;

        private final String singular;
        private final String plural;

        TimeTest(long timeInSeconds, String singular, String plural) {
            this.timeInSeconds = timeInSeconds;
            this.singular = singular;
            this.plural = plural;
        }

        public long getTimeInSeconds() {
            return timeInSeconds;
        }

        public String getSingular() {
            return singular;
        }

        public String getPlural() {
            return plural;
        }
    }
}
