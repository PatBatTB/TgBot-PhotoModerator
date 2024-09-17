package com.github.patbattb.tgbot_photomoderator.service.handling.message;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;

/**
 * Executor for message type level handling
 */
@FunctionalInterface
public interface MessageTypeExecutor {
    void execute(MethodContainer methodContainer);
}
