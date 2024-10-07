package com.github.patbattb.tgbot_photomoderator.service.handling.callback.replymenu;

import com.github.patbattb.tgbot_photomoderator.component.DataContainer;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.component.UserGroup;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.commands.DeleteMyCommands;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeChat;

@UtilityClass
@Slf4j
public class CallbackLeaveGroupExecutor {
    public static void yes(MethodContainer methodContainer) {
        Long userId = methodContainer.getUpdate().getCallbackQuery().getFrom().getId();
        UserGroup userGroup = methodContainer.getUserGroup();
        EditMessageText editMessageText = new EditMessageText();
        editMessageText.setMessageId(methodContainer.getMessageId());
        editMessageText.setChatId(methodContainer.getChatId());
        if (DataContainer.Container.removeFromGroup(userGroup, userId)) {
            methodContainer.getMethodList()
                    .add(new DeleteMyCommands(new BotCommandScopeChat(methodContainer.getChatId()), null));
            editMessageText.setText("Вы были удалены из группы \"" + userGroup.getName() + "\".");
        } else {
            editMessageText.setText("Произошла внутренняя ошибка.\n" +
                    "Вы не были удалены из группы \"" + userGroup.getName() + "\".");
        }
        methodContainer.getMethodList().add(editMessageText);
    }

    public static void no(MethodContainer methodContainer) {
        DeleteMessage deleteMessage = new DeleteMessage(methodContainer.getChatId(), methodContainer.getMessageId());
        methodContainer.getMethodList().add(deleteMessage);
    }

    public static void unknown(MethodContainer methodContainer) {
        log.error("default method");
    }
}
