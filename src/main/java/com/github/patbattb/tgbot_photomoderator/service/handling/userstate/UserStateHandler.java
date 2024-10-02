package com.github.patbattb.tgbot_photomoderator.service.handling.userstate;

import com.github.patbattb.tgbot_photomoderator.component.UserState;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class UserStateHandler {
    private final Map<UserState, Executable> CHAT_STATE_MAP = Map.of(
            UserState.ADD_ADMINISTRATOR, UserStateExecutor::addAdmin,
            UserState.ADD_MODERATOR, UserStateExecutor::addModerator,
            UserState.DEL_ADMINISTRATOR, UserStateExecutor::removeAdministrator
    );
    private final Executable CHAT_STATE_DEFAULT = UserStateExecutor::defaultState;

    public void process(MethodContainer methodContainer) {
        CHAT_STATE_MAP.getOrDefault(methodContainer.getUser().state(), CHAT_STATE_DEFAULT).execute(methodContainer);
    }
}
