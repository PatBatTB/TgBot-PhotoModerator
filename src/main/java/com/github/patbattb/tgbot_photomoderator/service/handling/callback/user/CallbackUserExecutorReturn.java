package com.github.patbattb.tgbot_photomoderator.service.handling.callback.user;

import com.github.patbattb.tgbot_photomoderator.component.AdminPanelTitle;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.keyboard.KeyboardProvider;
import lombok.Value;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@Value
public class CallbackUserExecutorReturn implements CallbackUserExecutor {
    @Override
    public void execute(MethodContainer methodContainer) {
        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .text(AdminPanelTitle.MAIN_TITLE)
                .replyMarkup(KeyboardProvider.getMainAdminPanel())
                .build();
        methodContainer.getMethodList().add(editMessageText);
    }
}
