package com.github.patbattb.tgbot_photomoderator.service.handling.command.executor;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import lombok.Value;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Value
public class CommandTypeExecutorStop implements CommandTypeExecutor {
    @Override
    public void execute(MethodContainer methodContainer) {
        //TODO
        //mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), this.toString()));
    }
}
