package com.github.patbattb.tgbot_photomoderator.component;


import java.util.Objects;

public record User(String id, String firstName, String lastName, String userName, ChatState state) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static class Updater {
        private String id;
        private String firstName;
        private String lastName;
        private String userName;
        private ChatState state;

        public Updater(User user) {
            this.id = user.id;
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.userName = user.userName;
            this.state = user.state;
        }

        public Updater chatState(ChatState state) {
            this.state = state;
            return  this;
        }

        public User update() {
            return new User(this.id, this.firstName, this.lastName, this.userName, this.state);
        }

    }
}