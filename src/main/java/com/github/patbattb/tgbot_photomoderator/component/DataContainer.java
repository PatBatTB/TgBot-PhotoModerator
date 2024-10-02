package com.github.patbattb.tgbot_photomoderator.component;

import com.github.patbattb.tgbot_photomoderator.service.json.JsonHandler;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public final class DataContainer {

    @Getter
    @Setter
    @SuppressWarnings("InstantiationOfUtilityClass")
    private static Container container = new Container();


    /**
     * Method for initialize static Container
     */
    public static void init() {
        JsonHandler.loadData();
    }

    public static class Container {
        @SuppressWarnings("FieldMayBeFinal") //Makes not final for GSON lib
        private static Map<UserGroup, Set<String>> userGroupMap;
        @Getter
        @Setter
        private static String channel;
        @SuppressWarnings("FieldMayBeFinal")
        private static Set<User> userList;

        static {
            userGroupMap = Map.of(
                    UserGroup.ADMIN, new HashSet<>(),
                    UserGroup.MODERATOR, new HashSet<>(),
                    UserGroup.BANNED, new HashSet<>()
            );
            channel = "";
            userList = new HashSet<>();
        }

        public static boolean addToGroup(UserGroup userGroup, String userName) {
            Optional<String> optional = getId(userName);
            if (optional.isPresent()) {
                String id = optional.get();
                userGroupMap.values().forEach(elem -> elem.removeIf(id::equals));
                boolean result = userGroupMap.get(userGroup).add(id);
                if (result) JsonHandler.saveData();
                return result;
            }
            return false;
        }

        public static boolean findInGroup(UserGroup userGroup, String userName) {
            Optional<String> optional = getId(userName);
            return optional.filter(id -> userGroupMap.get(userGroup).contains(id)).isPresent();
        }

        public static boolean removeFromGroup(UserGroup userGroup, String userName) {
            Optional<String> optional = getId(userName);
            if (optional.isPresent()) {
                String id = optional.get();
                boolean result = userGroupMap.get(userGroup).remove(id);
                if (result) JsonHandler.saveData();
                return result;
            }
            return false;
        }

        public static boolean addToList(User user) {
            Container.userList.removeIf(user::equals);
            boolean result = userList.add(user);
            if (result) JsonHandler.saveData();
            return result;
        }

        public static UserState getChatState(String chatId) {
            Optional<UserState> optional = userList.stream()
                    .filter(elem -> Objects.equals(elem.id(), chatId))
                    .map(User::state)
                    .findAny();
            return optional.orElse(UserState.NO_STATE);
        }

        @SuppressWarnings("all")
        public static boolean setChatState(String chatId, UserState state) {
            Optional<User> optional = userList.stream()
                    .filter(elem -> Objects.equals(elem.id(), chatId))
                    .findAny();
            return optional.filter(user -> addToList(new User.Updater(user).userState(state).update())).isPresent();

        }

        public static UserGroup getUserGroup(String userId) {
            Optional<UserGroup> optional = userGroupMap.entrySet().stream()
                    .filter(elem -> elem.getValue().contains(userId))
                    .map(Map.Entry::getKey)
                    .findAny();
            return optional.orElse(UserGroup.OTHER);
        }

        private static Optional<String> getId(String userName) {
            return userList.stream()
                    .filter(elem -> Objects.equals(elem.userName(), userName))
                    .map(User::id)
                    .findAny();
        }

        /**
         * The {@code User.state} field in the {@link User} class is {@link UserState} enum, so it cannot have any value.
         * In case of a data violation in the file, all undefined values for the {@code User.state} field
         * are replaced by the default value {@link UserState#NO_STATE}
         * @return {@code true} if any data has been corrected.
         */
        public static boolean fixIncorrectUserState() {
            boolean result = false;
            Iterator<User> iterator = userList.iterator();
            Set<User> modifiedUsers = new HashSet<>();
            while (iterator.hasNext()) {
                User user = iterator.next();
                if (user.state() == null) {
                    modifiedUsers.add(new User.Updater(user).userState(UserState.NO_STATE).update());
                    iterator.remove();
                    result = true;
                }
            }
            userList.addAll(modifiedUsers);
            return result;
        }
    }
}
