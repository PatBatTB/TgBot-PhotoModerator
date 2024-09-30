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
        return new InlineKeyboardMarkup(List.of(
                List.of(getButton(level, InlineButton.CHANNEL),
                        getButton(level, InlineButton.USER)),
                List.of(getButton(level, InlineButton.CLOSE))
        ));
    }

    public InlineKeyboardMarkup getAdminUserKeyboardMarkup(String level) {
        return new InlineKeyboardMarkup(List.of(
                List.of(getButton(level, InlineButton.ADMIN),
                        getButton(level, InlineButton.MODERATOR)),
                List.of(getButton(level, InlineButton.BAN),
                        getButton(level, InlineButton.RETURN))
        ));
    }

    public InlineKeyboardMarkup getAdminChannelKeyboardMarkup(String level) {
        return new InlineKeyboardMarkup(List.of(
                List.of(getButton(level, InlineButton.SET_CHANNEL),
                        getButton(level, InlineButton.REMOVE_CHANNEL)),
                List.of(getButton(level, InlineButton.RETURN))
        ));
    }

    public InlineKeyboardMarkup getYesNoKeyboardMarkup(String level) {
        return new InlineKeyboardMarkup(List.of(
                List.of(getButton(level, InlineButton.YES),
                        getButton(level, InlineButton.NO))
        ));
    }

    public InlineKeyboardMarkup getAdminControlUserKeyboardMarkup(String level) {
        return new InlineKeyboardMarkup(List.of(
                List.of(getButton(level, InlineButton.ADD),
                        getButton(level, InlineButton.REMOVE)),
                List.of(getButton(level, InlineButton.RETURN))
        ));
    }

    private InlineKeyboardButton getButton(String level, InlineButton button) {
        return InlineKeyboardButton.builder()
                .text(button.getText())
                .callbackData(CallBackParser.build(level, button.getData()))
                .build();
    }
}
