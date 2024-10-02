package com.github.patbattb.tgbot_photomoderator.service.handling.callback.replymenu;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackDelAdminHandler {
    private final Map<String, Executable> DEL_ADMIN_MAP = Map.of(
            InlineButton.YES.getData(), CallbackDelAdminExecutor::yes,
            InlineButton.NO.getData(), CallbackDelAdminExecutor::no
    );
    private final Executable DEL_ADMIN_DEFAULT = CallbackDelAdminExecutor::unknown;

    public static void process(MethodContainer methodContainer) {
        DEL_ADMIN_MAP.getOrDefault(methodContainer.getCallbackData().button(), DEL_ADMIN_DEFAULT)
                .execute(methodContainer);
    }
}
