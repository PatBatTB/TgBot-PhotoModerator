package com.github.patbattb.tgbot_photomoderator.service.markup;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.CallBackParser;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@UtilityClass
public class KeyboardMarkupProvider {

    public InlineKeyboardMarkup getAdminMainKeyboardMarkup(String level) {
        var channelButton = getButton(level, InlineButton.CHANNEL);
        var userButton = getButton(level, InlineButton.USER);
        var rowOne = List.of(channelButton, userButton);
        var closeButton = getButton(level, InlineButton.CLOSE);
        var rowTwo = List.of(closeButton);
        return new InlineKeyboardMarkup(List.of(rowOne, rowTwo));
    }

    public InlineKeyboardMarkup getAdminUserKeyboardMarkup(String level) {
        var adminButton = getButton(level, InlineButton.ADMIN);
        var moderatorButton = getButton(level, InlineButton.MODERATOR);
        var banButton = getButton(level, InlineButton.BAN);
        var returnButton = getButton(level, InlineButton.RETURN);
        var rowOne = List.of(adminButton, moderatorButton);
        var rowTwo = List.of(banButton, returnButton);
        return new InlineKeyboardMarkup(List.of(rowOne, rowTwo));
    }

    public InlineKeyboardMarkup getAdminChannelKeyboardMarkup(String level) {
        return new InlineKeyboardMarkup(List.of(
                List.of(
                        getButton(level, InlineButton.SET_CHANNEL),
                        getButton(level, InlineButton.REMOVE_CHANNEL)
                        ),
                List.of(
                        getButton(level, InlineButton.RETURN)
                )
                ));
    }

    public InlineKeyboardMarkup getAdminControlUserKeyboardMarkup(String level) {
        var addButton = getButton(level, InlineButton.ADD);
        var removeButton = getButton(level, InlineButton.REMOVE);
        var returnButton = getButton(level, InlineButton.RETURN);
        var rowOne = List.of(addButton, removeButton);
        var rowTwo = List.of(returnButton);
        return new InlineKeyboardMarkup(List.of(rowOne, rowTwo));
    }

    private InlineKeyboardButton getButton(String level, InlineButton button) {
        return InlineKeyboardButton.builder()
                .text(button.getText())
                .callbackData(CallBackParser.build(level, button.getData()))
                .build();
    }
}
