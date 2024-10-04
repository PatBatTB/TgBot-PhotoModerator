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
    ADD_USER_TO_ADMIN_AGAIN("addUserToAdminLevel"),
    ADD_USER_TO_MODERATOR_AGAIN("addUserToModeratorLevel"),
    DEL_USER_FROM_ADMIN_AGAIN("delUserFromAdminLevel"),
    DEL_USER_FROM_BAN_AGAIN("delUserFromBanLevel"),
    DEL_USER_FROM_MODERATOR_AGAIN("delUserFromModeratorLevel"),
    LEAVE_GROUP("leaveGroupLevel");

    private final String data;

    InlineLevel(String data) {
        this.data = data;
    }
}
