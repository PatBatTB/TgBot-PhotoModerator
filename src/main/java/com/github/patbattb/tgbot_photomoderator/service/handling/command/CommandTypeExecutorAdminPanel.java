package com.github.patbattb.tgbot_photomoderator.service.handling.command;

import com.github.patbattb.tgbot_photomoderator.component.AdminPanelTitle;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.keyboard.KeyboardProvider;
import lombok.Value;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@Value
public class CommandTypeExecutorAdminPanel implements CommandTypeExecutor {

    @Override
    public void execute(MethodContainer methodContainer) {
        SendMessage sendMessage = new SendMessage(methodContainer.getChatId(), AdminPanelTitle.MAIN_TITLE);
        sendMessage.setReplyMarkup(KeyboardProvider.getMainAdminPanel());
        methodContainer.getMethodList().add(sendMessage);
    }
}
