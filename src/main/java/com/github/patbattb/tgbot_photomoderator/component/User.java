package com.github.patbattb.tgbot_photomoderator.component;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public record User(String id, String firstName, String lastName, String userName, UserState state) {

    public static String trimUserName(String userName) {
        Pattern pattern = Pattern.compile("(^[^@].+|(?<=^@).+)");
        Matcher matcher = pattern.matcher(userName);
        return matcher.find() ? matcher.group() : userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    public boolean deepEquals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) &&
                Objects.equals(firstName, user.firstName) &&
                Objects.equals(lastName, user.lastName) &&
                Objects.equals(userName, user.userName) &&
                Objects.equals(state, user.state);


    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    public static class Updater {
        private final String id;
        private final String firstName;
        private final String lastName;
        private final String userName;
        private UserState state;

        public Updater(User user) {
            this.id = user.id;
            this.firstName = user.firstName;
            this.lastName = user.lastName;
            this.userName = user.userName;
            this.state = user.state;
        }

        public Updater userState(UserState state) {
            this.state = state;
            return this;
        }

        public User update() {
            return new User(this.id, this.firstName, this.lastName, this.userName, this.state);
        }

    }
}