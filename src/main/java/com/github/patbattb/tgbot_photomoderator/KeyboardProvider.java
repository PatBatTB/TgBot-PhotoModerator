package com.github.patbattb.tgbot_photomoderator;

import lombok.Value;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class KeyboardProvider {

    static InlineKeyboardMarkup getYesNoKeyboardMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton buttonYes = new InlineKeyboardButton("Yes");
        InlineKeyboardButton buttonNo = new InlineKeyboardButton("No");
        buttonYes.setCallbackData("Yes");
        buttonNo.setCallbackData("No");
        buttonList.add(buttonYes);
        buttonList.add(buttonNo);
        keyboard.add(buttonList);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }

    static InlineKeyboardMarkup getConfirmKeyboardMarkup() {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
        List<InlineKeyboardButton> buttonList = new ArrayList<>();
        InlineKeyboardButton buttonConfirm = InlineKeyboardButton.builder()
                .text("Confirm")
                .callbackData("Confirm")
                .build();
        buttonList.add(buttonConfirm);
        keyboard.add(buttonList);
        inlineKeyboardMarkup.setKeyboard(keyboard);
        return inlineKeyboardMarkup;
    }
}
