package com.github.patbattb.tgbot_photomoderator.service.handling.update;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;

/**
 * Executor for update type level handling.
 */
@FunctionalInterface
public interface UpdateTypeExecutor {
    void execute(MethodContainer methodContainer);
}
