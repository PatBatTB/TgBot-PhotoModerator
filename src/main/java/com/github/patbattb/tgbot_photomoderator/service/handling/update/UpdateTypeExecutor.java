package com.github.patbattb.tgbot_photomoderator.service.handling.update;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.CallbackLevelHandler;
import com.github.patbattb.tgbot_photomoderator.service.handling.message.MessageTypeHandler;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
public class UpdateTypeExecutor {

    public void message(MethodContainer methodContainer) {
        MessageTypeHandler.process(methodContainer);
    }

    public void callbackQuery(MethodContainer methodContainer) {
        CallbackLevelHandler.process(methodContainer);
    }

    public void unknown(MethodContainer methodContainer) {
        log.error("default method");
    }
}
