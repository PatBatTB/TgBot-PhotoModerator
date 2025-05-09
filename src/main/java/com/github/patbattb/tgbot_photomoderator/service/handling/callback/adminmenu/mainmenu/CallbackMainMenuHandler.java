package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.mainmenu;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackMainMenuHandler {
    private final Map<String, Executable> callbackAdminExecutorMap = Map.of(
            InlineButton.CHANNEL.getData(), CallbackMainMenuExecutor::channel,
            InlineButton.USER.getData(), CallbackMainMenuExecutor::user,
            InlineButton.CLOSE.getData(), CallbackMainMenuExecutor::close,
            InlineButton.LOCATION.getData(), CallbackMainMenuExecutor::location
    );
    private final Executable callbackAdminExecutorDefault = CallbackMainMenuExecutor::unknown;

    public void process(MethodContainer methodContainer) {
        callbackAdminExecutorMap.getOrDefault(methodContainer.getCallbackData().button(), callbackAdminExecutorDefault)
                .execute(methodContainer);
    }
}
