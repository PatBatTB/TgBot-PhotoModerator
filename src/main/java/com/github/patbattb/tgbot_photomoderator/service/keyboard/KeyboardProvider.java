package com.github.patbattb.tgbot_photomoderator.service.keyboard;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.InlineLevel;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.CallBackParser;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@UtilityClass
public class KeyboardProvider {

    public InlineKeyboardMarkup getMainAdminPanel() {
        String level = InlineLevel.ADMIN.getName();
        var channelButton = InlineKeyboardButton.builder()
                .text(InlineButton.CHANNEL.getText())
                .callbackData(CallBackParser.build(level, InlineButton.CHANNEL.getName()))
                .build();
        var userButton = InlineKeyboardButton.builder()
                .text(InlineButton.USER.getText())
                .callbackData(CallBackParser.build(level, InlineButton.USER.getName()))
                .build();
        var rowOne = List.of(channelButton, userButton);
        var closeButton = InlineKeyboardButton.builder()
                .text(InlineButton.CLOSE.getText())
                .callbackData(CallBackParser.build(level, InlineButton.CLOSE.getName()))
                .build();
        var rowTwo = List.of(closeButton);
        return new InlineKeyboardMarkup(List.of(rowOne, rowTwo));
    }
}
