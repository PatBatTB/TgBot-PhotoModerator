package com.github.patbattb.tgbot_photomoderator.service.handling.callback.admin;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;

@FunctionalInterface
public interface CallbackAdminExecutor {
    void execute(MethodContainer methodContainer);
}
