package com.github.patbattb.tgbot_photomoderator.service.handling.callback.user;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;

@FunctionalInterface
public interface CallbackUserExecutor {
    void execute(MethodContainer methodContainer);
}
