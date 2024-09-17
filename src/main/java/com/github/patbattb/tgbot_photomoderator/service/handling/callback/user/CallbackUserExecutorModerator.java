package com.github.patbattb.tgbot_photomoderator.service.handling.callback.user;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import lombok.Value;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Value
public class CallbackUserExecutorModerator implements CallbackUserExecutor {
    @Override
    public void execute(MethodContainer methodContainer) {
        //TODO
        //Mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), this.toString()));
    }
}
