package com.github.patbattb.tgbot_photomoderator.service.handling.message;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.command.CommandTypeHandler;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@UtilityClass
public class MessageTypeExecutor {

    public void text(MethodContainer methodContainer) {
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "MessageTypeExecutor - text"));
    }

    public void command(MethodContainer methodContainer) {
        CommandTypeHandler.process(methodContainer);
    }

    public void unknown(MethodContainer methodContainer) {
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "MessageTypeExecutor - unknown"));
    }

}
