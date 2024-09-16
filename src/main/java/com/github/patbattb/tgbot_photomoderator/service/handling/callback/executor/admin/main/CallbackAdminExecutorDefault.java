package com.github.patbattb.tgbot_photomoderator.service.handling.callback.executor.admin.main;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import lombok.Value;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Value
public class CallbackAdminExecutorDefault implements CallbackAdminExecutor {
    @Override
    public void execute(MethodContainer methodContainer) {
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), this.toString()));
    }
}
