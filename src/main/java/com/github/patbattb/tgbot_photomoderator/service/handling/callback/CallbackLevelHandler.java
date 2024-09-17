package com.github.patbattb.tgbot_photomoderator.service.handling.callback;

import com.github.patbattb.tgbot_photomoderator.component.InlineLevel;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackLevelHandler {
    private final Map<String, Executable> callbackTypeExecutorMap = Map.of(
            InlineLevel.ADMIN_MAIN.getName(), CallbackLevelExecutor::admin,
            InlineLevel.ADMIN_USER.getName(), CallbackLevelExecutor::user,
            InlineLevel.ADMIN_CHANNEL.getName(), CallbackLevelExecutor::channel,
            InlineLevel.ADMIN_CONTROL_ADMIN.getName(), CallbackLevelExecutor::controlAdmin,
            InlineLevel.ADMIN_CONTROL_MODERATOR.getName(), CallbackLevelExecutor::controlModerator,
            InlineLevel.ADMIN_CONTROL_BAN.getName(), CallbackLevelExecutor::controlBan
    );
    private final Executable CALLBACK_LEVEL_EXECUTOR_DEFAULT = CallbackLevelExecutor::unknown;

    public void process(MethodContainer methodContainer) {
        callbackTypeExecutorMap.getOrDefault(methodContainer.getCallbackData().LEVEL(), CALLBACK_LEVEL_EXECUTOR_DEFAULT)
                .execute(methodContainer);
    }
}
