package com.github.patbattb.tgbot_photomoderator.service.handling.chat;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.update.UpdateTypeHandler;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@UtilityClass
public class ChatTypeExecutor {

    public void privateChat(MethodContainer methodContainer) {
        UpdateTypeHandler.process(methodContainer);
    }

    public void groupChat(MethodContainer methodContainer) {
        //TODO
        //Mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "ChatTypeExecutor - groupChat"));
    }

    public void unknown(MethodContainer methodContainer) {
        //TODO
        //Mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "ChatTypeExecutor - unknown"));
    }
}
