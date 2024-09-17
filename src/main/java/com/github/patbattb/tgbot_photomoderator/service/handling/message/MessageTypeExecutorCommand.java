package com.github.patbattb.tgbot_photomoderator.service.handling.message;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.command.CommandTypeHandler;
import lombok.Value;

@Value
public class MessageTypeExecutorCommand implements MessageTypeExecutor {
    @Override
    public void execute(MethodContainer methodContainer) {
        //TODO
        //Mock
        CommandTypeHandler.process(methodContainer);
    }
}
