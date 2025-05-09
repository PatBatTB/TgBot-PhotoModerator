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
public class CallbackDelAdminExecutor {
    public void yes(MethodContainer methodContainer) {
        EditMessageText message = new EditMessageText("Введите UserName пользователя еще раз.");
        message.setMessageId(methodContainer.getMessageId());
        message.setChatId(methodContainer.getChatId());
        DataContainer.Container.setUserState(methodContainer.getUser().id(), UserState.DEL_ADMINISTRATOR);
        methodContainer.getMethodList().add(message);
    }

    public void no(MethodContainer methodContainer) {
        DeleteMessage deleteMessage = new DeleteMessage(methodContainer.getChatId(), methodContainer.getMessageId());
        methodContainer.getMethodList().add(deleteMessage);
    }

    public void unknown(MethodContainer methodContainer) {
        log.error("default method");
    }
}
