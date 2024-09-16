package com.github.patbattb.tgbot_photomoderator.service.handling.callback.executor.admin;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.executor.admin.main.*;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackAdminHandler {
    private final Map<String, CallbackAdminExecutor> callbackAdminExecutorMap = Map.of(
            InlineButton.CHANNEL.getName(), new CallbackAdminExecutorChannel(),
            InlineButton.USER.getName(), new CallbackAdminExecutorUser(),
            InlineButton.CLOSE.getName(), new CallbackAdminExecutorClose()
    );
    private final CallbackAdminExecutor callbackAdminExecutorDefault = new CallbackAdminExecutorDefault();

    public void process(MethodContainer methodContainer) {
        callbackAdminExecutorMap.getOrDefault(methodContainer.getCallbackData().BUTTON(), callbackAdminExecutorDefault)
                .execute(methodContainer);
    }
}
