package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.channel;

import com.github.patbattb.tgbot_photomoderator.component.AdminPanelTitle;
import com.github.patbattb.tgbot_photomoderator.component.InlineLevel;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@UtilityClass
public class CallbackChannelExecutor {


    public void setChannel(MethodContainer methodContainer) {
        //TODO
        //mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "CallbackChannelExecutor - setChannel"));
    }

    public void removeChannel(MethodContainer methodContainer) {
        //TODO
        //mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "CallbackChannelExecutor - removeChannel"));
    }

    public void returning(MethodContainer methodContainer) {
        EditMessageText editMessage = EditMessageText.builder()
                .text(AdminPanelTitle.ADMIN_MAIN_TITLE)
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .replyMarkup(KeyboardMarkupProvider
                        .getAdminMainKeyboardMarkup(InlineLevel.ADMIN_MAIN))
                .build();
        methodContainer.getMethodList().add(editMessage);
    }


    public void unknown(MethodContainer methodContainer) {
        //TODO
        //mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "CallbackChannelExecutor - unknown"));
    }
}
