package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adduser;

import com.github.patbattb.tgbot_photomoderator.component.ChatState;
import com.github.patbattb.tgbot_photomoderator.component.DataContainer;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@UtilityClass
public class CallbackAddAdminExecutor {
    public static void yes(MethodContainer methodContainer) {
        EditMessageText message = new EditMessageText("Введите UserName пользователя еще раз.");
        message.setMessageId(methodContainer.getMessageId());
        message.setChatId(methodContainer.getChatId());
        DataContainer.Container.setChatState(methodContainer.getUser().id(), ChatState.ADD_ADMINISTRATOR);
        methodContainer.getMethodList().add(message);
    }

    public static void no(MethodContainer methodContainer) {
        DeleteMessage deleteMessage = new DeleteMessage(methodContainer.getChatId(), methodContainer.getMessageId());
        methodContainer.getMethodList().add(deleteMessage);
    }

    public static void unknown(MethodContainer methodContainer) {
        //TODO
        //mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "CallbackAddAdminExecutor - unknown"));
    }
}
