package com.github.patbattb.tgbot_photomoderator.component;

import lombok.Getter;

@Getter
public enum InlineButton {
    CHANNEL("channelButton", "Канал"),
    SET_CHANNEL("setChannelButton", "Указать канал"),
    USER("userButton","Пользователи"),
    ADMIN("adminButton","Администраторы"),
    MODERATOR("moderatorButton", "Модераторы"),
    BAN("banControl", "Бан"),
    ADD("addButton","Добавить"),
    REMOVE("RemoveButton","Удалить"),
    RETURN("returnButton","Назад"),
    CLOSE("closeButton", "Закрыть");

    private final String data;
    private final String text;

    InlineButton(String data, String text) {
        this.data = data;
        this.text = text;
    }
}
