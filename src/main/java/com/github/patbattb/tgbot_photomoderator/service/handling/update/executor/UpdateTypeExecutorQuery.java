package com.github.patbattb.tgbot_photomoderator.service.handling.update.executor;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.CallbackLevelHandler;
import lombok.Value;

@Value
public class UpdateTypeExecutorQuery implements UpdateTypeExecutor {
    @Override
    public void execute(MethodContainer methodContainer) {
        CallbackLevelHandler.process(methodContainer);
    }

}
