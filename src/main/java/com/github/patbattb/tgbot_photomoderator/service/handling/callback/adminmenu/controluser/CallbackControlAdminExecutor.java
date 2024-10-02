package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.controluser;

import com.github.patbattb.tgbot_photomoderator.component.*;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@UtilityClass
public class CallbackControlAdminExecutor {
    public void add(MethodContainer methodContainer) {
        DeleteMessage delete = new DeleteMessage(methodContainer.getChatId(), methodContainer.getMessageId());
        methodContainer.getMethodList().add(delete);
        DataContainer.Container.setChatState(methodContainer.getUser().id(), UserState.ADD_ADMINISTRATOR);
        SendMessage message = new SendMessage(methodContainer.getChatId(), "Укажите UserName пользователя\n" +
                "для добавления в список администраторов.");
        methodContainer.getMethodList().add(message);
    }

    public void remove(MethodContainer methodContainer) {
        DeleteMessage delete = new DeleteMessage(methodContainer.getChatId(), methodContainer.getMessageId());
        methodContainer.getMethodList().add(delete);
        DataContainer.Container.setChatState(methodContainer.getUser().id(), UserState.DEL_ADMINISTRATOR);
        SendMessage message = new SendMessage(methodContainer.getChatId(), "Укажите UserName пользователя\n" +
                "для удаления его из списка администраторов.");
        methodContainer.getMethodList().add(message);
    }

    public void returning(MethodContainer methodContainer) {
        EditMessageText editMessageText = EditMessageText.builder()
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .text(AdminPanelTitle.ADMIN_USER_TITLE)
                .replyMarkup(KeyboardMarkupProvider.getAdminUserKeyboardMarkup(InlineLevel.ADMIN_USER))
                .build();
        methodContainer.getMethodList().add(editMessageText);
    }

    public void unknown(MethodContainer methodContainer) {
        //TODO
        //mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "CallbackControlAdminExecutor - unknown"));
    }
}
