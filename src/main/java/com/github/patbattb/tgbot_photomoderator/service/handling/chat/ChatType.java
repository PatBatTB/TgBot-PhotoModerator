package com.github.patbattb.tgbot_photomoderator.service.handling.chat;

import lombok.Getter;

@Getter
public enum ChatType {
    PRIVATE("private"), GROUP("group"), SUPERGROUP("supergroup");

    private final String name;

    ChatType(String name) {
        this.name = name;
    }
}
