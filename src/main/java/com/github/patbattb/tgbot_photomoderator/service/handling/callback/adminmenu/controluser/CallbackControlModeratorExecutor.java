package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.controluser;

import com.github.patbattb.tgbot_photomoderator.component.*;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@UtilityClass
public class CallbackControlModeratorExecutor {

    public void add(MethodContainer methodContainer) {
        DeleteMessage delete = new DeleteMessage(methodContainer.getChatId(), methodContainer.getMessageId());
        methodContainer.getMethodList().add(delete);
        DataContainer.Container.setChatState(methodContainer.getUser().id(), UserState.ADD_MODERATOR);
        SendMessage messsage = new SendMessage(methodContainer.getChatId(), "Укажите UserName пользователя\n" +
                "для добавления в список модераторов.");
        methodContainer.getMethodList().add(messsage);
    }

    public void remove(MethodContainer methodContainer) {
        //TODO
        //mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "CallbackControlModeratorExecutor - remove"));
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
