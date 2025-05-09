package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.controluser;

import com.github.patbattb.tgbot_photomoderator.component.*;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@UtilityClass
@Slf4j
public class CallbackControlAdminExecutor {
    public void add(MethodContainer methodContainer) {
        DataContainer.Container.setUserState(methodContainer.getUser().id(), UserState.ADD_ADMINISTRATOR);
        DeleteMessage deleteMessage = new DeleteMessage(methodContainer.getChatId(), methodContainer.getMessageId());
        SendMessage message = new SendMessage(methodContainer.getChatId(),"Укажите UserName пользователя\n" +
                "для добавления в список администраторов.");
        methodContainer.getMethodList().add(deleteMessage);
        methodContainer.getMethodList().add(message);
    }

    public void remove(MethodContainer methodContainer) {
        DeleteMessage deleteMessage = new DeleteMessage(methodContainer.getChatId(), methodContainer.getMessageId());
        DataContainer.Container.setUserState(methodContainer.getUser().id(), UserState.DEL_ADMINISTRATOR);
        SendMessage message = new SendMessage(methodContainer.getChatId(),"Укажите UserName пользователя\n" +
                "для удаления его из списка администраторов.");
        methodContainer.getMethodList().add(deleteMessage);
        methodContainer.getMethodList().add(message);
    }

    public void returning(MethodContainer methodContainer) {
        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .text(AdminPanelTitle.USER_TITLE)
                .replyMarkup(KeyboardMarkupProvider.getAdminUserKeyboardMarkup(InlineLevel.ADMIN_USER))
                .build();
        methodContainer.getMethodList().add(editMessageText);
    }

    public void unknown(MethodContainer methodContainer) {
        log.error("default method");
    }
}
