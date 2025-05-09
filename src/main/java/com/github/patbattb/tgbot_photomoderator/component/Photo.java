package com.github.patbattb.tgbot_photomoderator.component;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;

public record Photo(String fileId, PhotoStatus status, Point point, LocalDateTime sendTime, String senderId, Set<AuthMessage> authMessages) {
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

    public static class Updater {

        private String fileId;
        private PhotoStatus status;
        private Point point;
        private LocalDateTime sendTime;
        private String senderId;
        private Set<AuthMessage> authMessages;

        public Updater(Photo photo) {
            this.fileId = photo.fileId;
            this.status = photo.status;
            this.point = photo.point;
            this.sendTime = photo.sendTime;
            this.senderId = photo.senderId;
            this.authMessages = photo.authMessages;
        }

        public Updater setStatus(PhotoStatus photoStatus) {
            this.status = photoStatus;
            return this;
        }

        public Updater setAuthMessages(Set<AuthMessage> authMessages) {
            this.authMessages = authMessages;
            return this;
        }

        public Photo update() {
            return new Photo(this.fileId, this.status, this.point, this.sendTime, this.senderId, this.authMessages);
        }
    }
}
