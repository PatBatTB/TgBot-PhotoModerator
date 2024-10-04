package com.github.patbattb.tgbot_photomoderator.service.handling.callback.replymenu;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackLeaveGroupHandler {
    private final Map<String, Executable> LEAVE_GROUP_MAP = Map.of(
            InlineButton.YES.getData(), CallbackLeaveGroupExecutor::yes,
            InlineButton.NO.getData(), CallbackLeaveGroupExecutor::no
    );
    private final Executable LEAVE_GROUP_DEFAULT = CallbackLeaveGroupExecutor::unknown;

    public void process(MethodContainer methodContainer) {
        LEAVE_GROUP_MAP.getOrDefault(methodContainer.getCallbackData().button(), LEAVE_GROUP_DEFAULT)
                .execute(methodContainer);
    }
}
