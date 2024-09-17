package com.github.patbattb.tgbot_photomoderator.service.handling.message;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

@UtilityClass
public class MessageTypeHandler {

    private final String COMMAND_PATTERN = "/";

    private final Map<MessageType, Executable> MESSAGE_TYPE_EXECUTOR_MAP = Map.of(
            MessageType.TEXT, MessageTypeExecutor::text,
            MessageType.COMMAND, MessageTypeExecutor::command
    );
    private final Executable MESSAGE_TYPE_EXECUTOR_DEFAULT = MessageTypeExecutor::unknown;

    public void process(MethodContainer methodContainer) {
        MESSAGE_TYPE_EXECUTOR_MAP.getOrDefault(parseMessageType(methodContainer), MESSAGE_TYPE_EXECUTOR_DEFAULT)
                .execute(methodContainer);
    }

    private MessageType parseMessageType (MethodContainer methodContainer) {
        Message message = methodContainer.getUpdate().getMessage();
        if (message.hasText() && message.getText().startsWith(COMMAND_PATTERN)) {
            return MessageType.COMMAND;
        } else if (message.hasText()) {
            return MessageType.TEXT;
        }
        return MessageType.UNKNOWN;
    }
}
