package com.github.patbattb.tgbot_photomoderator.component;

import java.time.LocalDateTime;
import java.util.Objects;

public record Photo(String fileId, PhotoStatus status, Point point, LocalDateTime sendTime, String senderId) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Photo photo = (Photo) o;
        return Objects.equals(fileId, photo.fileId);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(fileId);
    }
}
