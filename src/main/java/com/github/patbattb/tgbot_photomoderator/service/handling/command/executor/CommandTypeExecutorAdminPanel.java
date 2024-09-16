package com.github.patbattb.tgbot_photomoderator.service.handling.command.executor;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.keyboard.KeyboardProvider;
import lombok.Value;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Value
public class CommandTypeExecutorAdminPanel implements CommandTypeExecutor {
    String ADMIN_PANEL = "Панель администратора";

    @Override
    public void execute(MethodContainer methodContainer) {
        SendMessage sendMessage = new SendMessage(methodContainer.getChatId(), ADMIN_PANEL);
        sendMessage.setReplyMarkup(KeyboardProvider.getMainAdminPanel());
        methodContainer.getMethodList().add(sendMessage);
    }
}
