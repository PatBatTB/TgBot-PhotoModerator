package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.mainmenu;

import com.github.patbattb.tgbot_photomoderator.component.AdminPanelTitle;
import com.github.patbattb.tgbot_photomoderator.component.InlineLevel;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@UtilityClass
public class CallbackMainMenuExecutor {
    public void user(MethodContainer methodContainer) {
        EditMessageText editMessage = EditMessageText.builder()
                .text(AdminPanelTitle.ADMIN_USER_TITLE)
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .replyMarkup(KeyboardMarkupProvider
                        .getAdminUserKeyboardMarkup(InlineLevel.ADMIN_USER.getName()))
                .build();
        methodContainer.getMethodList().add(editMessage);
    }

    public void channel(MethodContainer methodContainer) {
        EditMessageText editMessage = EditMessageText.builder()
                .text(AdminPanelTitle.ADMIN_CHANNEL_TITLE)
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .replyMarkup(KeyboardMarkupProvider.
                        getAdminChannelKeyboardMarkup(InlineLevel.ADMIN_CHANNEL.getName()))
                .build();
        methodContainer.getMethodList().add(editMessage);
    }

    public void close(MethodContainer methodContainer) {
        DeleteMessage delete = new DeleteMessage(methodContainer.getChatId(), methodContainer.getMessageId());
        methodContainer.getMethodList().add(delete);
    }

    public void unknown(MethodContainer methodContainer) {
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "CallbackAdminExecutor - unknown"));
    }
}
