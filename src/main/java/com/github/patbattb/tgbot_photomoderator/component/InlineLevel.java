package com.github.patbattb.tgbot_photomoderator.component;

import lombok.Getter;

@Getter
public enum InlineLevel {
    ADMIN_MAIN("adminMainLevel"),
    ADMIN_CHANNEL("adminChannelLevel"),
    ADMIN_USER("adminUserLevel"),
    ADMIN_CONTROL_ADMIN("adminControlAdminLevel"),
    ADMIN_CONTROL_MODERATOR("adminControlModeratorLevel"),
    ADMIN_CONTROL_BAN("adminControlBanLevel"),
    ADD_USER_TO_ADMIN("addUserToAdmin");

    private final String name;

    InlineLevel(String name) {
        this.name = name;
    }
}
