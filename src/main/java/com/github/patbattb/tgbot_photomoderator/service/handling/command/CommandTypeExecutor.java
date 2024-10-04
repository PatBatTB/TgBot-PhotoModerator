package com.github.patbattb.tgbot_photomoderator.service.handling.command;

import com.github.patbattb.tgbot_photomoderator.component.*;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@UtilityClass
public class CommandTypeExecutor {

    public void adminPanel(MethodContainer methodContainer) {
        SendMessage sendMessage = new SendMessage(methodContainer.getChatId(), AdminPanelTitle.ADMIN_MAIN_TITLE);
        sendMessage.setReplyMarkup(KeyboardMarkupProvider
                .getAdminMainKeyboardMarkup(InlineLevel.ADMIN_MAIN));
        methodContainer.getMethodList().add(sendMessage);
    }

    public void run(MethodContainer methodContainer) {
        DataContainer.Container.setBotState(BotState.RUN);
        SendMessage message = new SendMessage(methodContainer.getChatId(), "Бот запущен.");
        methodContainer.getMethodList().add(message);
    }

    public void stop(MethodContainer methodContainer) {
        DataContainer.Container.setBotState(BotState.STOP);
        SendMessage message = new SendMessage(methodContainer.getChatId(), "Бот остановлен.");
        methodContainer.getMethodList().add(message);
    }

    public void leave(MethodContainer methodContainer) {
        String groupName = DataContainer.Container.getUserGroup(methodContainer.getUser().id()).getName();
        SendMessage message = new SendMessage(methodContainer.getChatId(), "Вы действительно хотите покинуть группу \"" + groupName + "\"?");
        message.setReplyMarkup(KeyboardMarkupProvider.getYesNoKeyboardMarkup(InlineLevel.LEAVE_GROUP));
        methodContainer.getMethodList().add(message);
    }

    public void error(MethodContainer methodContainer) {
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "Verification error."));
    }

    public void unknown(MethodContainer methodContainer) {
        //TODO
        //Mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "CommandTypeExecutor - unknown"));
    }
}
