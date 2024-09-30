package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.controluser;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackControlAdminHandler {
    private final Map<String, Executable> callbackControlAdminMap = Map.of(
            InlineButton.ADD.getData(), CallbackControlAdminExecutor::add,
            InlineButton.REMOVE.getData(), CallbackControlAdminExecutor::remove,
            InlineButton.RETURN.getData(), CallbackControlAdminExecutor::returning
    );
    private final Executable callbackControlAdminExecutorDefault = CallbackControlAdminExecutor::unknown;

    public void process(MethodContainer methodContainer) {
        callbackControlAdminMap.getOrDefault(methodContainer.getCallbackData().button(), callbackControlAdminExecutorDefault)
                .execute(methodContainer);
    }
}
