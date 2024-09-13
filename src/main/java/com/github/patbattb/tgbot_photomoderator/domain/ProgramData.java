package com.github.patbattb.tgbot_photomoderator.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.*;

public class ProgramData {
        @Getter
        private static Map<UserGroup, Set<String>> users;
        @Getter
        @Setter
        private static String channel;

        static {
                users = new HashMap<>() {{
                        put(UserGroup.ADMIN, Set.of());
                        put(UserGroup.MODERATOR, Set.of());
                        put(UserGroup.BANNED, Set.of());
                }};
                channel = "";
        }
}
