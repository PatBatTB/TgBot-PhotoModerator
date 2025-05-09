package com.github.patbattb.tgbot_photomoderator.service.handling.callback.replymenu;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackDelBanHandler {
    private final Map<String, Executable> DEL_BAN_MAP = Map.of(
            InlineButton.YES.getData(), CallbackDelBanExecutor::yes,
            InlineButton.NO.getData(), CallbackDelBanExecutor::no
    );
    private final Executable DEL_BAN_DEFAULT = CallbackDelBanExecutor::unknown;
    public void process(MethodContainer methodContainer) {
        DEL_BAN_MAP.getOrDefault(methodContainer.getCallbackData().button(), DEL_BAN_DEFAULT)
                .execute(methodContainer);
    }
}
