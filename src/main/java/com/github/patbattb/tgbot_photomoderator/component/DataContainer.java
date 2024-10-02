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

        @SuppressWarnings("unused")
        public static boolean removeFromGroup(UserGroup userGroup, String userId) {
            boolean result =  userGroupMap.get(userGroup).remove(userId);
            if (result) JsonHandler.saveData();
            return result;
        }

        public static boolean addToList(User user) {
            Container.userList.removeIf(user::equals);
            boolean result = userList.add(user);
            if (result) JsonHandler.saveData();
            return result;
        }

        public static ChatState getChatState(String chatId) {
            Optional<ChatState> optional = userList.stream()
                    .filter(elem -> Objects.equals(elem.id(), chatId))
                    .map(User::state)
                    .findAny();
            return optional.orElse(ChatState.NOSTATE);
        }

        @SuppressWarnings("all")
        public static boolean setChatState(String chatId, ChatState state) {
            Optional<User> optional = userList.stream()
                    .filter(elem -> Objects.equals(elem.id(), chatId))
                    .findAny();
            return optional.filter(user -> addToList(new User.Updater(user).chatState(state).update())).isPresent();

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

    }
}
