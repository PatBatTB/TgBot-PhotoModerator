package com.github.patbattb.tgbot_photomoderator.service.handling.callback;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.user.CallbackUserHandler;
import lombok.Value;

@Value
public class CallbackLevelExecutorUser implements CallbackLevelExecutor {
    @Override
    public void execute(MethodContainer methodContainer) {
        CallbackUserHandler.process(methodContainer);
    }
}
