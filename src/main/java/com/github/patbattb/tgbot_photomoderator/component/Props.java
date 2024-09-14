package com.github.patbattb.tgbot_photomoderator.component;

import com.github.patbattb.tgbot_photomoderator.service.json.JsonHandler;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Props {

        public static Container container = new Container();


        /**
         * Method for initialize static Container
         */
        public static void init() {
                JsonHandler.loadData();
        }

        public static class Container {
                private static Map<UserGroup, Set<String>> users;
                @Getter
                @Setter
                private static String channel;

                static {
                        users = Map.of(
                                UserGroup.ADMIN, new HashSet<>(),
                                UserGroup.MODERATOR, new HashSet<>(),
                                UserGroup.BANNED, new HashSet<>()
                                );
                        channel = "";
                }

                public static void addUser(UserGroup userGroup, String userId) {
                        if (users.get(userGroup).add(userId)) {
                                JsonHandler.saveData();
                        }
                }

                public static boolean findUser(UserGroup userGroup, String userId) {
                        boolean result = users.get(userGroup).contains(userId);
                        if (result) JsonHandler.saveData();
                        return result;
                }

                public static boolean removeUser(UserGroup userGroup, String userId) {
                        boolean result =  users.get(userGroup).remove(userId);
                        if (result) JsonHandler.saveData();
                        return result;
                }

        }
}
