package com.github.patbattb.tgbot_photomoderator.service.handling.method;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.menu.MenuProvider;
import com.github.patbattb.tgbot_photomoderator.service.handling.update.UpdateTypeHandler;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;

import java.util.List;

@UtilityClass
@Slf4j
public class MethodHandler {
    public List<BotApiMethod<?>> process(MethodContainer methodContainer) {
        methodContainer.getMethodList().add(MenuProvider.getGroupMenu(methodContainer));
        log.debug("{}[{}]({})\n",
                methodContainer.getUserName(),
                methodContainer.getChatId(),
                methodContainer.getType());
        UpdateTypeHandler.process(methodContainer);
        return methodContainer.getMethodList();
    }
}
