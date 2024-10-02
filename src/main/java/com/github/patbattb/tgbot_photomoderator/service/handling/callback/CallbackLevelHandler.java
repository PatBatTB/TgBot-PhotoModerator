package com.github.patbattb.tgbot_photomoderator.service.handling.callback;

import com.github.patbattb.tgbot_photomoderator.component.InlineLevel;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackLevelHandler {
    private final Map<String, Executable> callbackTypeExecutorMap = Map.of(
            InlineLevel.ADMIN_MAIN.getData(), CallbackLevelExecutor::admin,
            InlineLevel.ADMIN_USER.getData(), CallbackLevelExecutor::user,
            InlineLevel.ADMIN_CHANNEL.getData(), CallbackLevelExecutor::channel,
            InlineLevel.ADMIN_CONTROL_ADMIN.getData(), CallbackLevelExecutor::controlAdmin,
            InlineLevel.ADMIN_CONTROL_MODERATOR.getData(), CallbackLevelExecutor::controlModerator,
            InlineLevel.ADMIN_CONTROL_BAN.getData(), CallbackLevelExecutor::controlBan,
            InlineLevel.ADD_USER_TO_ADMIN_AGAIN.getData(), CallbackLevelExecutor::addAdmin,
            InlineLevel.ADD_USER_TO_MODERATOR_AGAIN.getData(), CallbackLevelExecutor::addModerator,
            InlineLevel.DEL_USER_FROM_ADMIN_AGAIN.getData(), CallbackLevelExecutor::removeAdmin

    );
    private final Executable CALLBACK_LEVEL_EXECUTOR_DEFAULT = CallbackLevelExecutor::unknown;

    public void process(MethodContainer methodContainer) {
        callbackTypeExecutorMap.getOrDefault(methodContainer.getCallbackData().level(), CALLBACK_LEVEL_EXECUTOR_DEFAULT)
                .execute(methodContainer);
    }
}
