package com.github.patbattb.tgbot_photomoderator.component;

import lombok.Getter;

@Getter
public enum UserGroup {
    ADMIN("Администратор"),
    MODERATOR("Модератор"),
    BANNED("Бан"),
    OTHER("Остальные");

    private final String name;

    UserGroup(String name) {
        this.name = name;
    }
}
