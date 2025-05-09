package com.github.patbattb.tgbot_photomoderator.service.handling;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;

/**
 * Interface {@code Executable} must be implemented by a class that is designed to perform bot actions.
 * The class must implement an {@code execute} method that takes a {@code MethodContainer} as an argument
 * to which {@code BotApiMethod} can be added for further execution by the bot.
 */
@FunctionalInterface
public interface Executable {
    void execute(MethodContainer methodContainer);
}
