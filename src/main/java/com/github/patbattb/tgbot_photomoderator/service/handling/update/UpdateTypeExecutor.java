package com.github.patbattb.tgbot_photomoderator.service.handling.update;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.CallbackLevelHandler;
import com.github.patbattb.tgbot_photomoderator.service.handling.message.MessageTypeHandler;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@UtilityClass
public class UpdateTypeExecutor {

    public void message(MethodContainer methodContainer) {
        MessageTypeHandler.process(methodContainer);
    }

    public void callbackQuery(MethodContainer methodContainer) {
        CallbackLevelHandler.process(methodContainer);
    }

    public void unknown(MethodContainer methodContainer) {
        //TODO
        //Mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "UpdateTypeExecutor - unknown"));
    }
}
