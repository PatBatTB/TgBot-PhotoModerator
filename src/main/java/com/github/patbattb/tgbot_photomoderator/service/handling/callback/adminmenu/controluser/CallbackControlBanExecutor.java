package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.controluser;

import com.github.patbattb.tgbot_photomoderator.component.AdminPanelTitle;
import com.github.patbattb.tgbot_photomoderator.component.InlineLevel;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@UtilityClass
public class CallbackControlBanExecutor {
    public void add(MethodContainer methodContainer) {
        //TODO
        //mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "CallbackControlBanExecutor - add"));
    }

    public void remove(MethodContainer methodContainer) {
        //TODO
        //mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "CallbackControlBanExecutor - remove"));
    }

    public void returning(MethodContainer methodContainer) {
        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .text(AdminPanelTitle.ADMIN_USER_TITLE)
                .replyMarkup(KeyboardMarkupProvider.getAdminUserKeyboardMarkup(InlineLevel.ADMIN_USER))
                .build();
        methodContainer.getMethodList().add(editMessageText);
    }

    public void unknown(MethodContainer methodContainer) {
        //TODO
        //mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "CallbackControlBanExecutor - unknown"));
    }
}
