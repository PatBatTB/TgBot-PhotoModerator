package com.github.patbattb.tgbot_photomoderator.service.handling.callback.admin;

import com.github.patbattb.tgbot_photomoderator.component.AdminPanelTitle;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.keyboard.KeyboardProvider;
import lombok.Value;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Value
public class CallbackAdminExecutorUser implements CallbackAdminExecutor {

    @Override
    public void execute(MethodContainer methodContainer) {
        EditMessageText editMessage = new EditMessageText(AdminPanelTitle.USER_TITLE);
        editMessage.setChatId(methodContainer.getChatId());
        editMessage.setMessageId(methodContainer.getMessageId());
        editMessage.setReplyMarkup(KeyboardProvider.getUserPanelKeyboard());
        methodContainer.getMethodList().add(editMessage);
    }

}
