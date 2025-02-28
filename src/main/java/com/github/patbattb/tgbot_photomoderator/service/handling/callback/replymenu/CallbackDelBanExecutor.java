package com.github.patbattb.tgbot_photomoderator.service.handling.callback.replymenu;

import com.github.patbattb.tgbot_photomoderator.component.DataContainer;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.component.UserState;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@UtilityClass
@Slf4j
public class CallbackDelBanExecutor {
    public void yes(MethodContainer methodContainer) {
        EditMessageText editMessageText = EditMessageText.builder()
                .messageId(methodContainer.getMessageId())
                .chatId(methodContainer.getChatId())
                .text("Введите UserName пользователя еще раз.")
                .build();
        DataContainer.Container.setUserState(methodContainer.getUser().id(), UserState.DEL_BAN);
        methodContainer.getMethodList().add(editMessageText);
    }

    public void no(MethodContainer methodContainer) {
        DeleteMessage delete = new DeleteMessage(methodContainer.getChatId(), methodContainer.getMessageId());
        methodContainer.getMethodList().add(delete);
    }

    public void unknown(MethodContainer methodContainer) {
        log.error("default method");
    }
}
