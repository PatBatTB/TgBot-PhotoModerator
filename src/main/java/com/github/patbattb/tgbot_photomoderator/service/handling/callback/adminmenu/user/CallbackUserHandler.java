package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.user;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackUserHandler {
        private final Map<String, Executable> callbackUserExecutorMap = Map.of(
                InlineButton.ADMIN.getData(), CallbackUserExecutor::admin,
                InlineButton.MODERATOR.getData(), CallbackUserExecutor::moderator,
                InlineButton.BAN.getData(), CallbackUserExecutor::ban,
                InlineButton.RETURN.getData(), CallbackUserExecutor::returninig
        );
        private final Executable callbackUserExecutorDefault = CallbackUserExecutor::unknown;

        public void process(MethodContainer methodContainer) {
            callbackUserExecutorMap.getOrDefault(methodContainer.getCallbackData().button(), callbackUserExecutorDefault)
                    .execute(methodContainer);
        }
}
