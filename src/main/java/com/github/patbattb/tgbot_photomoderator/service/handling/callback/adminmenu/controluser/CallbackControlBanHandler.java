package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.controluser;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackControlBanHandler {
    private final Map<String, Executable> callbackControlBanMap = Map.of(
            InlineButton.ADD.getData(), CallbackControlBanExecutor::add,
            InlineButton.REMOVE.getData(), CallbackControlBanExecutor::remove,
            InlineButton.RETURN.getData(), CallbackControlBanExecutor::returning
    );
    private final Executable callbackControlBanDefault = CallbackControlBanExecutor::unknown;

    public void process(MethodContainer methodContainer) {
        callbackControlBanMap.getOrDefault(methodContainer.getCallbackData().button(), callbackControlBanDefault)
                .execute(methodContainer);
    }
}
