package com.github.patbattb.tgbot_photomoderator.service.handling.callback.user;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackUserHandler {
        private final Map<String, CallbackUserExecutor> callbackUserExecutorMap = Map.of(
                InlineButton.ADMIN_CONTROL.getName(), new CallbackUserExecutorAdmin(),
                InlineButton.MODERATOR_CONTROL.getName(), new CallbackUserExecutorModerator(),
                InlineButton.BAN_CONTROL.getName(), new CallbackUserExecutorBan(),
                InlineButton.RETURN.getName(), new CallbackUserExecutorReturn()
        );
        private final CallbackUserExecutor callbackUserExecutorDefault = new CallbackUserExecutorDefault();

        public void process(MethodContainer methodContainer) {
            callbackUserExecutorMap.getOrDefault(methodContainer.getCallbackData().BUTTON(), callbackUserExecutorDefault)
                    .execute(methodContainer);
        }
}
