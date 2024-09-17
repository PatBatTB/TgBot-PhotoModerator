package com.github.patbattb.tgbot_photomoderator.service.handling.update;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.message.MessageTypeHandler;
import lombok.Value;

@Value
public class UpdateTypeExecutorMessage implements UpdateTypeExecutor {
    @Override
    public void execute(MethodContainer methodContainer) {
        MessageTypeHandler.process(methodContainer);
    }
}
