package com.github.patbattb.tgbot_photomoderator.service.handling.update.executor;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import lombok.Value;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Value
public class UpdateTypeExecutorDefault implements UpdateTypeExecutor {
    @Override
    public void execute(MethodContainer methodContainer) {
        //TODO
        //Mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), this.toString()));
    }
}
