package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adduser;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackAddAdminHandler {
    private final Map<String, Executable> ADD_ADMIN_MAP = Map.of(
            InlineButton.YES.getData(), CallbackAddAdminExecutor::yes,
            InlineButton.NO.getData(), CallbackAddAdminExecutor::no
    );
    private final Executable ADD_ADMIN_DEFAULT = CallbackAddAdminExecutor::unknown;

    public void process(MethodContainer methodContainer) {
        ADD_ADMIN_MAP.getOrDefault(methodContainer.getCallbackData().button(), ADD_ADMIN_DEFAULT)
                .execute(methodContainer);
    }
}
