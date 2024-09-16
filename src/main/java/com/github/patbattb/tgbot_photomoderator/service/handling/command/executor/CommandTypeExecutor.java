package com.github.patbattb.tgbot_photomoderator.service.handling.command.executor;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;

/**
 * Executor for command type level handling.
 */
@FunctionalInterface
public interface CommandTypeExecutor {
    void execute(MethodContainer methodContainer);
}
