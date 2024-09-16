package com.github.patbattb.tgbot_photomoderator.service.handling.callback;

import com.github.patbattb.tgbot_photomoderator.component.InlineLevel;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.executor.CallbackLevelExecutor;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.executor.CallbackLevelExecutorAdmin;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.executor.CallbackLevelExecutorDefault;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackLevelHandler {
    private final Map<String, CallbackLevelExecutor> callbackTypeExecutorMap = Map.of(
            InlineLevel.ADMIN.getName(), new CallbackLevelExecutorAdmin()
    );
    private final CallbackLevelExecutor CALLBACK_LEVEL_EXECUTOR_DEFAULT = new CallbackLevelExecutorDefault();

    public void process(MethodContainer methodContainer) {
        callbackTypeExecutorMap.getOrDefault(methodContainer.getCallbackData().LEVEL(), CALLBACK_LEVEL_EXECUTOR_DEFAULT)
                .execute(methodContainer);
    }
}
