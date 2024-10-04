package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.controluser;

import com.github.patbattb.tgbot_photomoderator.component.*;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@UtilityClass
public class CallbackControlModeratorExecutor {

    public void add(MethodContainer methodContainer) {
        DataContainer.Container.setUserState(methodContainer.getUser().id(), UserState.ADD_MODERATOR);
        EditMessageText message = new EditMessageText("Укажите UserName пользователя\n" +
                "для добавления в список модераторов.");
        message.setMessageId(methodContainer.getMessageId());
        message.setChatId(methodContainer.getChatId());
        methodContainer.getMethodList().add(message);
    }

    public void remove(MethodContainer methodContainer) {
        DataContainer.Container.setUserState(methodContainer.getUser().id(), UserState.DEL_MODERATOR);
        EditMessageText message = EditMessageText.builder()
                .text("Укажите UserName пользователя\n" +
                        "для удаления его из списка модераторов.")
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .build();
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
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "CallbackControlModeratorExecutor - unknown"));
    }
}
