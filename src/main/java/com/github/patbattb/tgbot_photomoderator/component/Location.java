package com.github.patbattb.tgbot_photomoderator.component;

import com.github.patbattb.tgbot_photomoderator.service.json.Exclude;
import lombok.Getter;

public class Location {
    private double x1 = 0.0;
    private double x2 = 0.0;
    private double y1 = 0.0;
    private double y2 = 0.0;
    @Exclude
    @Getter
    private boolean ready = false;

    @Exclude
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Location.class);

    public boolean addCoordinates(Point point) {
        if (x1 == 0.0 && x2 == 0.0 && y1 == 0.0 && y2 == 0.0) {
            x1 = point.x();
            y1 = point.y();
            return true;
        } else if (x2 == 0.0 && y2 == 0.0) {
            x2 = Math.max(x1, point.x());
            x1 = Math.min(x1, point.x());
            y2 = Math.max(y1, point.y());
            y1 = Math.min(y1, point.y());
            ready = true;
            return true;
        }
        log.error(String.format("X1 = %f, X2 = %f, Y1 = %f, Y2 = %f, argX = %f, argY = %f",
                x1, x2, y1, y2, point.x(), point.y()));
        return false;
    }

    public boolean checkoutPoint(Point point) {
        return point.x() >= x1 && point.x() <= x2 && point.y() >= y1 && point.y() <= y2;
    }
}
