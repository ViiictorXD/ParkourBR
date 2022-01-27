package com.rukko.parkour.backend.format;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatBalance {

    private static final Pattern PATTERN = Pattern.compile("^(\\d+\\.?\\d*)(\\D+)");

    private static final List<String> suffixes = Arrays.asList("", "K", "M", "B", "T", "Q", "L");

    public static String formatNumber(double value) {
        int index = 0;

        double tmp;
        while ((tmp = value / 1000) >= 1) {
            value = tmp;
            ++index;
        }

        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        return decimalFormat.format(value) + suffixes.get(index);
    }

    public static double parseString(String value) {
        try {
            return Double.parseDouble(value);
        } catch (Exception ignored) { }

        Matcher matcher = PATTERN.matcher(value);
        if (!matcher.find()) {
            return -1;
        }

        double amount = Double.parseDouble(matcher.group(1));
        String suffix = matcher.group(2);

        int index = suffixes.indexOf(suffix.toUpperCase());

        return amount * Math.pow(1000, index);
    }
}
