package com.github.patbattb.tgbot_photomoderator.service.handling.command;

import com.github.patbattb.tgbot_photomoderator.component.*;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@UtilityClass
@Slf4j
public class CommandTypeExecutor {

    public void adminPanel(MethodContainer methodContainer) {
        SendMessage sendMessage = new SendMessage(methodContainer.getChatId(), AdminPanelTitle.MAIN_TITLE);
        sendMessage.setReplyMarkup(KeyboardMarkupProvider
                .getAdminMainKeyboardMarkup(InlineLevel.ADMIN_MAIN));
        methodContainer.getMethodList().add(sendMessage);
    }

    public static void start(MethodContainer methodContainer) {
        SendMessage message = new SendMessage(methodContainer.getChatId(), "Привет!\n" +
                "Данный бот принимает фотографии от пользователей, и публикует их на канале после модерации.\n\n" +
                "Теперь можно взаимодействовать с ботом и отправлять ему фотографии.");
        methodContainer.getMethodList().add(message);
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
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "Вам недоступна данная комманда"));
    }

    public void unknown(MethodContainer methodContainer) {
        log.error("default method");
    }
}
