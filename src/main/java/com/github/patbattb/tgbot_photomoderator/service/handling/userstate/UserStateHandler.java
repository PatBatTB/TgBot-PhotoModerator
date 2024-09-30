package com.github.patbattb.tgbot_photomoderator.service.handling.userstate;

import com.github.patbattb.tgbot_photomoderator.component.ChatState;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class UserStateHandler {
    private final Map<ChatState, Executable> CHAT_STATE_MAP = Map.of(
            ChatState.ADD_ADMINISTRATOR, UserStateExecutor::addAdmin
    );
    private final Executable CHAT_STATE_DEFAULT = UserStateExecutor::defaultState;

    public void process(MethodContainer methodContainer) {
        CHAT_STATE_MAP.getOrDefault(methodContainer.getUser().state(), CHAT_STATE_DEFAULT).execute(methodContainer);
    }
}
