package com.github.patbattb.tgbot_photomoderator.service.handling.update;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class UpdateTypeHandler {

    private final Map<UpdateType, Executable> UPDATE_TYPE_EXECUTOR_MAP = Map.of(
            UpdateType.MESSAGE, UpdateTypeExecutor::message,
            UpdateType.CALLBACK_QUERY, UpdateTypeExecutor::callbackQuery
    );
    private final Executable UPDATE_TYPE_EXECUTOR_DEFAULT = UpdateTypeExecutor::unknown;

    public void process(MethodContainer methodContainer) {
        UPDATE_TYPE_EXECUTOR_MAP.getOrDefault(methodContainer.getType(), UPDATE_TYPE_EXECUTOR_DEFAULT)
                .execute(methodContainer);
    }
}
