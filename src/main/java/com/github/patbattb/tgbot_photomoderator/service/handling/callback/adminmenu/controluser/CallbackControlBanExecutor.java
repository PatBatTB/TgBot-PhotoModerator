package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.controluser;

import com.github.patbattb.tgbot_photomoderator.component.*;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@UtilityClass
@Slf4j
public class CallbackControlBanExecutor {
    public void add(MethodContainer methodContainer) {
        DataContainer.Container.setUserState(methodContainer.getUser().id(), UserState.ADD_BAN);
        EditMessageText message = new EditMessageText("Укажите UserName пользователя\n" +
                "для добавления его в бан.");
        message.setChatId(methodContainer.getChatId());
        message.setMessageId(methodContainer.getMessageId());
        methodContainer.getMethodList().add(message);
    }

    public void remove(MethodContainer methodContainer) {
        DataContainer.Container.setUserState(methodContainer.getUser().id(), UserState.DEL_BAN);
        EditMessageText message = EditMessageText.builder()
                .text("Укажите UserName пользователя\n" +
                        "для удаления его из бана.")
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .build();
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
