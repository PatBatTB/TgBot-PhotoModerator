package com.github.patbattb.tgbot_photomoderator.component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record Point(double x, double y) {

    public static Point get(String coordString) {
        Pattern pattern = Pattern.compile("\\d+\\.\\d+");
        Matcher matcher = pattern.matcher(coordString);
        List<String> matches = new ArrayList<>();
        while (matcher.find()) {
            matches.add(matcher.group());
        }
        if (matches.size() != 2) {
            return null;
        }
        double x = Double.parseDouble(matches.get(0));
        double y = Double.parseDouble(matches.get(1));
        return new Point(x, y);
    }

    @Override
    public String toString() {
        return x + ", " + y;
    }
}
