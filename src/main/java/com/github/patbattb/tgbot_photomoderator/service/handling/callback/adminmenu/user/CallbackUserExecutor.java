package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.user;

import com.github.patbattb.tgbot_photomoderator.component.AdminPanelTitle;
import com.github.patbattb.tgbot_photomoderator.component.InlineLevel;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@UtilityClass
@Slf4j
public class CallbackUserExecutor {
    public void admin(MethodContainer methodContainer) {
        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .text(AdminPanelTitle.CONTROL_ADMIN_TITLE)
                .replyMarkup(KeyboardMarkupProvider
                        .getAdminControlUserKeyboardMarkup(InlineLevel.ADMIN_CONTROL_ADMIN))
                .build();
        methodContainer.getMethodList().add(editMessageText);
    }

    public void moderator(MethodContainer methodContainer) {
        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .text(AdminPanelTitle.CONTROL_MODERATOR_TITLE)
                .replyMarkup(KeyboardMarkupProvider
                        .getAdminControlUserKeyboardMarkup(InlineLevel.ADMIN_CONTROL_MODERATOR))
                .build();
        methodContainer.getMethodList().add(editMessageText);
    }

    public void ban(MethodContainer methodContainer) {
        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .text(AdminPanelTitle.CONTROL_BAN_TITLE)
                .replyMarkup(KeyboardMarkupProvider
                        .getAdminControlUserKeyboardMarkup(InlineLevel.ADMIN_CONTROL_BAN))
                .build();
        methodContainer.getMethodList().add(editMessageText);
    }

    public void returninig(MethodContainer methodContainer) {
        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .text(AdminPanelTitle.MAIN_TITLE)
                .replyMarkup(KeyboardMarkupProvider
                        .getAdminMainKeyboardMarkup(InlineLevel.ADMIN_MAIN))
                .build();
        methodContainer.getMethodList().add(editMessageText);
    }

    public void unknown(MethodContainer methodContainer) {
        log.error("default method");
    }
}
