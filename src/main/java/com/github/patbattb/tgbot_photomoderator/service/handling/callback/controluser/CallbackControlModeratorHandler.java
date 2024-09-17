package com.github.patbattb.tgbot_photomoderator.service.handling.callback.controluser;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackControlModeratorHandler {
    private final Map<String, Executable> callbackControlModeratorMap = Map.of(
            InlineButton.ADD.getData(), CallbackControlModeratorExecutor::add,
            InlineButton.REMOVE.getData(), CallbackControlModeratorExecutor::remove,
            InlineButton.RETURN.getData(), CallbackControlModeratorExecutor::returning
    );
    private final Executable callbackControlModeratorDefault = CallbackControlModeratorExecutor::unknown;

    public void process(MethodContainer methodContainer) {
        callbackControlModeratorMap.getOrDefault(methodContainer.getCallbackData().BUTTON(), callbackControlModeratorDefault)
                .execute(methodContainer);
    }
}
