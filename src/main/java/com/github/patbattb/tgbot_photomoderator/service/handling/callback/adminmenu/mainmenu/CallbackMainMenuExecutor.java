package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.mainmenu;

import com.github.patbattb.tgbot_photomoderator.component.AdminPanelTitle;
import com.github.patbattb.tgbot_photomoderator.component.InlineLevel;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@UtilityClass
@Slf4j
public class CallbackMainMenuExecutor {
    public void user(MethodContainer methodContainer) {
        EditMessageText editMessage = EditMessageText.builder()
                .text(AdminPanelTitle.USER_TITLE)
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .replyMarkup(KeyboardMarkupProvider
                        .getAdminUserKeyboardMarkup(InlineLevel.ADMIN_USER))
                .build();
        methodContainer.getMethodList().add(editMessage);
    }

    public void channel(MethodContainer methodContainer) {
        EditMessageText editMessage = EditMessageText.builder()
                .text(AdminPanelTitle.CHANNEL_TITLE)
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .replyMarkup(KeyboardMarkupProvider.
                        getAdminChannelKeyboardMarkup(InlineLevel.ADMIN_CHANNEL))
                .build();
        methodContainer.getMethodList().add(editMessage);
    }

    public static void location(MethodContainer methodContainer) {
        EditMessageText editMessageText = EditMessageText.builder()
                .text(AdminPanelTitle.LOCATION_TITLE)
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .replyMarkup(KeyboardMarkupProvider.
                        getAdminLocationKeyboardMarkup(InlineLevel.ADMIN_LOCATION))
                .build();
        methodContainer.getMethodList().add(editMessageText);
    }

    public void close(MethodContainer methodContainer) {
        DeleteMessage delete = new DeleteMessage(methodContainer.getChatId(), methodContainer.getMessageId());
        methodContainer.getMethodList().add(delete);
    }

    public void unknown(MethodContainer methodContainer) {
        log.error("default method");
    }
}
