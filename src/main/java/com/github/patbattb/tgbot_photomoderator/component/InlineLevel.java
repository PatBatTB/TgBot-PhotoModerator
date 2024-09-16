package com.github.patbattb.tgbot_photomoderator.component;

import lombok.Getter;

@Getter
public enum InlineLevel {
    ADMIN("adminLevel"),
    CHANNEL("channelLevel"),
    USER("userLevel"),
    ADMIN_CONTROL("adminControlLevel"),
    MODERATOR_CONTROL("moderatorControlLevel"),
    BAN_CONTROL("banControlLevel");

    private final String name;

    InlineLevel(String name) {
        this.name = name;
    }
}
