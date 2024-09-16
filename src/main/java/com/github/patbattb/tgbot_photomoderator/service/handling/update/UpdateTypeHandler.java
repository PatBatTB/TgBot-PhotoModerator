package com.github.patbattb.tgbot_photomoderator.service.handling.update;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.update.executor.UpdateTypeExecutor;
import com.github.patbattb.tgbot_photomoderator.service.handling.update.executor.UpdateTypeExecutorDefault;
import com.github.patbattb.tgbot_photomoderator.service.handling.update.executor.UpdateTypeExecutorMessage;
import com.github.patbattb.tgbot_photomoderator.service.handling.update.executor.UpdateTypeExecutorQuery;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class UpdateTypeHandler {

    private final Map<UpdateType, UpdateTypeExecutor> UPDATE_TYPE_EXECUTOR_MAP = Map.of(
            UpdateType.MESSAGE, new UpdateTypeExecutorMessage(),
            UpdateType.CALLBACK_QUERY, new UpdateTypeExecutorQuery()
    );
    private final UpdateTypeExecutor UPDATE_TYPE_EXECUTOR_DEFAULT = new UpdateTypeExecutorDefault();

    public void process(MethodContainer methodContainer) {
        UPDATE_TYPE_EXECUTOR_MAP.getOrDefault(methodContainer.getType(), UPDATE_TYPE_EXECUTOR_DEFAULT)
                .execute(methodContainer);
    }
}
