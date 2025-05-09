package com.github.patbattb.tgbot_photomoderator.component;

import lombok.Getter;

/**
 * Don't use underline symbol in {@link InlineButton#data} field.
 */
@Getter
public enum InlineButton {
    CHANNEL("channelButton", "Канал"),
    SET_CHANNEL("setChannelButton", "Указать канал"),
    REMOVE_CHANNEL("removeChannelButton", "Удалить канал"),
    LOCATION("locationButton", "Локация"),
    USER("userButton","Пользователи"),
    ADMIN("adminButton","Администраторы"),
    MODERATOR("moderatorButton", "Модераторы"),
    BAN("banControl", "Бан"),
    ADD("addButton","Добавить"),
    REMOVE("RemoveButton","Удалить"),
    RETURN("returnButton","Назад"),
    CLOSE("closeButton", "Закрыть"),
    YES("yesButton", "Да"),
    NO("noButton", "Нет"),
    SET_LOCATION("setLocationButton", "Задать локацию"),
    VERIFY_PHOTO("verifyPhotoButton", "Одобрить.");

    private final String data;
    private final String text;

    InlineButton(String data, String text) {
        this.data = data;
        this.text = text;
    }
}
