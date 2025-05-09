package com.github.patbattb.tgbot_photomoderator.service.handling.callback.replymenu;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackDelModeratorHandler {
    private final Map<String, Executable> DEL_MODERATOR_MAP = Map.of(
            InlineButton.YES.getData(), CallbackDelModeratorExecutor::yes,
            InlineButton.NO.getData(), CallbackDelModeratorExecutor::no
    );
    private final Executable DEL_MODERATOR_DEFAULT = CallbackDelModeratorExecutor::unknown;

    public void process(MethodContainer methodContainer) {
        DEL_MODERATOR_MAP.getOrDefault(methodContainer.getCallbackData().button(), DEL_MODERATOR_DEFAULT)
                .execute(methodContainer);
    }
}
