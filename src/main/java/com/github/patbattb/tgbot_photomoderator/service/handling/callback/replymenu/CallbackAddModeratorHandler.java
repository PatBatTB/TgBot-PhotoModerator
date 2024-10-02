package com.github.patbattb.tgbot_photomoderator.service.handling.callback.replymenu;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackAddModeratorHandler {
    private final Map<String, Executable> ADD_MODERATOR_MAP = Map.of(
            InlineButton.YES.getData(), CallbackAddModeratorExecutor::yes,
            InlineButton.NO.getData(), CallbackAddModeratorExecutor::no
    );
    private final Executable ADD_MODERATOR_DEFAULT = CallbackAddModeratorExecutor::unknown;

    public void process(MethodContainer methodContainer) {
        ADD_MODERATOR_MAP.getOrDefault(methodContainer.getCallbackData().button(), ADD_MODERATOR_DEFAULT)
                .execute(methodContainer);
    }
}
