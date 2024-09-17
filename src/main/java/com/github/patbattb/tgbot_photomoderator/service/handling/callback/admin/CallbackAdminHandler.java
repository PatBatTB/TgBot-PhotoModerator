package com.github.patbattb.tgbot_photomoderator.service.handling.callback.admin;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackAdminHandler {
    private final Map<String, Executable> callbackAdminExecutorMap = Map.of(
            InlineButton.CHANNEL.getData(), CallbackAdminExecutor::channel,
            InlineButton.USER.getData(), CallbackAdminExecutor::user,
            InlineButton.CLOSE.getData(), CallbackAdminExecutor::close
    );
    private final Executable callbackAdminExecutorDefault = CallbackAdminExecutor::unknown;

    public void process(MethodContainer methodContainer) {
        callbackAdminExecutorMap.getOrDefault(methodContainer.getCallbackData().BUTTON(), callbackAdminExecutorDefault)
                .execute(methodContainer);
    }
}
