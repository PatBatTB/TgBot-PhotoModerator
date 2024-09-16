package com.github.patbattb.tgbot_photomoderator.service.handling.callback.executor;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;

@FunctionalInterface
public interface CallbackLevelExecutor {
    void execute(MethodContainer methodContainer);
}
