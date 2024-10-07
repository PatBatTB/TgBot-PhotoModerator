package com.github.patbattb.tgbot_photomoderator.service.handling.callback;

import com.github.patbattb.tgbot_photomoderator.component.InlineLevel;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class CallbackLevelHandler {
    private final Map<String, Executable> callbackTypeExecutorMap = new HashMap<>() {{
        put(InlineLevel.ADMIN_MAIN.getData(),CallbackLevelExecutor::admin);
        put(InlineLevel.ADMIN_USER.getData(), CallbackLevelExecutor::user);
        put(InlineLevel.ADMIN_CHANNEL.getData(), CallbackLevelExecutor::channel);
        put(InlineLevel.ADMIN_CONTROL_ADMIN.getData(), CallbackLevelExecutor::controlAdmin);
        put(InlineLevel.ADMIN_CONTROL_MODERATOR.getData(), CallbackLevelExecutor::controlModerator);
        put(InlineLevel.ADMIN_CONTROL_BAN.getData(), CallbackLevelExecutor::controlBan);
        put(InlineLevel.ADD_USER_TO_ADMIN_AGAIN.getData(), CallbackLevelExecutor::addAdmin);
        put(InlineLevel.ADD_USER_TO_MODERATOR_AGAIN.getData(), CallbackLevelExecutor::addModerator);
        put(InlineLevel.DEL_USER_FROM_ADMIN_AGAIN.getData(), CallbackLevelExecutor::removeAdmin);
        put(InlineLevel.DEL_USER_FROM_MODERATOR_AGAIN.getData(), CallbackLevelExecutor::removeModerator);
        put(InlineLevel.DEL_USER_FROM_BAN_AGAIN.getData(), CallbackLevelExecutor::removeBan);
        put(InlineLevel.LEAVE_GROUP.getData(), CallbackLevelExecutor::leaveGroup);
        put(InlineLevel.ADMIN_LOCATION.getData(), CallbackLevelExecutor::location);
    }};
    private final Executable CALLBACK_LEVEL_EXECUTOR_DEFAULT = CallbackLevelExecutor::unknown;

    public void process(MethodContainer methodContainer) {
        callbackTypeExecutorMap.getOrDefault(methodContainer.getCallbackData().level(), CALLBACK_LEVEL_EXECUTOR_DEFAULT)
                .execute(methodContainer);
    }
}
