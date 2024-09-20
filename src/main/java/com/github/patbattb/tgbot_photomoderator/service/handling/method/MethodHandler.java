package com.github.patbattb.tgbot_photomoderator.service.handling.method;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.chat.ChatTypeHandler;
import com.github.patbattb.tgbot_photomoderator.service.menu.MenuProvider;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

@UtilityClass
@Slf4j
public class MethodHandler {
    public void process(MethodContainer methodContainer) {
        methodContainer.getMethodList().add(MenuProvider.getGroupMenu(methodContainer));
        log.debug("{}[{}]({})\n",
                methodContainer.getUserName(),
                methodContainer.getChatId(),
                methodContainer.getType());
        ChatTypeHandler.process(methodContainer);
        try {
            for (BotApiMethod<?> method: methodContainer.getMethodList()) {
                methodContainer.getBot().execute(method);
            }
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }
}
