package com.github.patbattb.tgbot_photomoderator.service.handling.callback.executor;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.executor.admin.CallbackAdminHandler;
import lombok.Value;

@Value
public class CallbackLevelExecutorAdmin implements CallbackLevelExecutor {
    @Override
    public void execute(MethodContainer methodContainer) {
        CallbackAdminHandler.process(methodContainer);
    }
}
