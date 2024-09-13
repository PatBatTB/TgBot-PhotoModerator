package com.github.patbattb.tgbot_photomoderator.service.keyboard;

import com.github.patbattb.tgbot_photomoderator.button.InlineButton;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

@UtilityClass
public class KeyboardProvider {

    public InlineKeyboardMarkup getYesNoKeyboardMarkup() {
        InlineKeyboardButton buttonYes = InlineKeyboardButton.builder()
                .text(InlineButton.YES.getText())
                .callbackData(InlineButton.YES.getData())
                .build();
        InlineKeyboardButton buttonNo = InlineKeyboardButton.builder()
                .text(InlineButton.NO.getText())
                .callbackData(InlineButton.NO.getData())
                .build();
        return new InlineKeyboardMarkup(List.of(List.of(buttonYes, buttonNo)));
    }

    public InlineKeyboardMarkup getConfirmKeyboardMarkup() {
        InlineKeyboardButton buttonConfirm = InlineKeyboardButton.builder()
                .text(InlineButton.CONFIRM.getText())
                .callbackData(InlineButton.CONFIRM.getData())
                .build();
        return new InlineKeyboardMarkup(List.of(List.of(buttonConfirm)));
    }
}
