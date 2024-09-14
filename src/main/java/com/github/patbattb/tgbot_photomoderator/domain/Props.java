package com.github.patbattb.tgbot_photomoderator.domain;

import com.github.patbattb.tgbot_photomoderator.service.json.JsonHandler;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class Props {

        public static Container container = new Container();


        /**
         * Mock method for initialize static class.
         */
        public static void init() {
                JsonHandler.loadData();
        }

        public static class Container {
                @Getter
                static Map<UserGroup, Set<String>> users;
                @Getter
                @Setter
                static String channel;

                static {
                        users = Map.of(
                                UserGroup.ADMIN, new HashSet<>(),
                                UserGroup.MODERATOR, new HashSet<>(),
                                UserGroup.BANNED, new HashSet<>()
                                );
                        channel = "";
                }
        }
}
