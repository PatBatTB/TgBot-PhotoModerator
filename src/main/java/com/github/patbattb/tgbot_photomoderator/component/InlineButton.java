package com.github.patbattb.tgbot_photomoderator.component;

import lombok.Getter;

@Getter
public enum InlineButton {
    CHANNEL("channelButton", "Канал"),
    SET_CHANNEL("setChannelButton", "Указать канал"),
    USER("userButton","Пользователи"),
    ADMIN_CONTROL("adminControlButton","Админы"),
    MODERATOR_CONTROL("moderatorControlButton", "Модераторы"),
    BAN_CONTROL("banControl", "Бан"),
    ADD("addButton","Добавить"),
    REMOVE("RemoveButton","Удалить"),
    RETURN("returnButton","Назад"),
    CLOSE("closeButton", "Закрыть");

    private final String name;
    private final String text;

    InlineButton(String name, String text) {
        this.name = name;
        this.text = text;
    }
}
