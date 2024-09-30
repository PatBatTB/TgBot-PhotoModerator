package com.github.patbattb.tgbot_photomoderator.component;

import com.github.patbattb.tgbot_photomoderator.service.json.JsonHandler;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public final class DataContainer {

        @Getter
        @Setter
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

                public static boolean addUser(UserGroup userGroup, String userName) {
                        Optional<User> optional = userList.stream()
                                .filter(elem -> Objects.equals(elem.userName(), userName))
                                .findAny();
                        boolean result = optional.filter(elem -> Container.userGroupMap.get(userGroup).add(elem.id())).isPresent();
                        if (result) JsonHandler.saveData();
                        return result;
                }

                public static boolean findUser(UserGroup userGroup, String userName) {
                        Optional<User> optional = userList.stream()
                                .filter(elem -> Objects.equals(elem.userName(), userName))
                                .findAny();
                        return optional.filter(user -> userGroupMap.get(userGroup).contains(user.id())).isPresent();
                }

                public static boolean removeUser(UserGroup userGroup, String userId) {
                        boolean result =  Container.userGroupMap.get(userGroup).remove(userId);
                        if (result) JsonHandler.saveData();
                        return result;
                }

                public static boolean addUserToList(User user) {
                        Container.userList.removeIf(user::equals);
                        boolean result = Container.userList.add(user);
                        if (result) JsonHandler.saveData();
                        return result;
                }

                public static ChatState getUserChatState(String chatId) {
                        Optional<ChatState> optional = Container.userList.stream()
                                .filter(elem -> Objects.equals(elem.id(), chatId))
                                .map(User::state)
                                .findAny();
                        return optional.orElse(ChatState.NOSTATE);
                }

                public static boolean setUserChatState(String chatId, ChatState state) {
                        Optional<User> optional = Container.userList.stream()
                                .filter(elem -> Objects.equals(elem.id(), chatId))
                                .findAny();
                        return optional.filter(user -> addUserToList(new User.Updater(user).chatState(state).update())).isPresent();

                }

                public static UserGroup getUserGroupMap(String userId) {
                        for (Map.Entry<UserGroup, Set<String>> entry: userGroupMap.entrySet()) {
                                for (String value: entry.getValue()) {
                                        if (value.equals(userId)) {
                                                return entry.getKey();
                                        }
                                }
                        }
                        return UserGroup.OTHER;
                }

        }
}
