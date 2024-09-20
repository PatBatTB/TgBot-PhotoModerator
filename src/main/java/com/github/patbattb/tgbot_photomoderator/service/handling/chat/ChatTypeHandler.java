package com.github.patbattb.tgbot_photomoderator.service.handling.chat;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class ChatTypeHandler {

    private final Map<String, Executable> chatTypeExecutorMap = Map.of(
            ChatType.PRIVATE.getName(), ChatTypeExecutor::privateChat,
            ChatType.GROUP.getName(), ChatTypeExecutor::groupChat,
            ChatType.SUPERGROUP.getName(), ChatTypeExecutor::groupChat
    );
    private final Executable chatTypeExecutorDefault = ChatTypeExecutor::unknown;

    public void process(MethodContainer methodContainer) {
        String chatType = methodContainer.getChatType();
        chatTypeExecutorMap.getOrDefault(chatType, chatTypeExecutorDefault).execute(methodContainer);
    }
}
