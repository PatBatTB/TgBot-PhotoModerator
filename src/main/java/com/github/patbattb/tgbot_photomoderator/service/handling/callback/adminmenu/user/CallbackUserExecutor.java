package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.user;

import com.github.patbattb.tgbot_photomoderator.component.AdminPanelTitle;
import com.github.patbattb.tgbot_photomoderator.component.InlineLevel;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@UtilityClass
public class CallbackUserExecutor {
    public void admin(MethodContainer methodContainer) {
        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .text(AdminPanelTitle.ADMIN_CONTROL_ADMIN_TITLE)
                .replyMarkup(KeyboardMarkupProvider
                        .getAdminControlUserKeyboardMarkup(InlineLevel.ADMIN_CONTROL_ADMIN.getName()))
                .build();
        methodContainer.getMethodList().add(editMessageText);
    }

    public void moderator(MethodContainer methodContainer) {
        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .text(AdminPanelTitle.ADMIN_CONTROL_MODERATOR_TITLE)
                .replyMarkup(KeyboardMarkupProvider
                        .getAdminControlUserKeyboardMarkup(InlineLevel.ADMIN_CONTROL_MODERATOR.getName()))
                .build();
        methodContainer.getMethodList().add(editMessageText);
    }

    public void ban(MethodContainer methodContainer) {
        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .text(AdminPanelTitle.ADMIN_CONTROL_BAN_TITLE)
                .replyMarkup(KeyboardMarkupProvider
                        .getAdminControlUserKeyboardMarkup(InlineLevel.ADMIN_CONTROL_BAN.getName()))
                .build();
        methodContainer.getMethodList().add(editMessageText);
    }

    public void returninig(MethodContainer methodContainer) {
        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .text(AdminPanelTitle.ADMIN_MAIN_TITLE)
                .replyMarkup(KeyboardMarkupProvider
                        .getAdminMainKeyboardMarkup(InlineLevel.ADMIN_MAIN.getName()))
                .build();
        methodContainer.getMethodList().add(editMessageText);
    }

    public void unknown(MethodContainer methodContainer) {
        //TODO
        //Mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "CallbackUserExecutor - unknown"));
    }
}
