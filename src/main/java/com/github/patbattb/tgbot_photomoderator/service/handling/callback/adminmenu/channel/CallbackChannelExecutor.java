package com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.channel;

import com.github.patbattb.tgbot_photomoderator.component.AdminPanelTitle;
import com.github.patbattb.tgbot_photomoderator.component.DataContainer;
import com.github.patbattb.tgbot_photomoderator.component.InlineLevel;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

@UtilityClass
@Slf4j
public class CallbackChannelExecutor {

    public void setChannel(MethodContainer methodContainer) {
        DeleteMessage deleteMessage = new DeleteMessage(methodContainer.getChatId(), methodContainer.getMessageId());
        SendMessage sendMessage = new SendMessage(methodContainer.getChatId(), """
                Для того, что бы задать канал \
                для публикации фотографий \\- выполните следующие действия:
                
                \\- Пригласите бота на канал
                
                \\- Добавьте бота в администраторы этого канала
                
                \\- напишите на канале команду `/setchannel`
                
                \\(Для назначения канала вы и бот должны быть там администраторами\\)""");
        sendMessage.enableMarkdownV2(true);
        methodContainer.getMethodList().add(deleteMessage);
        methodContainer.getMethodList().add(sendMessage);
    }

    public void removeChannel(MethodContainer methodContainer) {
        DeleteMessage deleteMessage = new DeleteMessage(methodContainer.getChatId(), methodContainer.getMessageId());
        DataContainer.Container.setChannel("");
        SendMessage sendMessage = new SendMessage(methodContainer.getChatId(), "Адрес канала для публикации фотографий был удален.");
        methodContainer.getMethodList().add(deleteMessage);
        methodContainer.getMethodList().add(sendMessage);
    }

    public void returning(MethodContainer methodContainer) {
        EditMessageText editMessage = EditMessageText.builder()
                .text(AdminPanelTitle.MAIN_TITLE)
                .chatId(methodContainer.getChatId())
                .messageId(methodContainer.getMessageId())
                .replyMarkup(KeyboardMarkupProvider
                        .getAdminMainKeyboardMarkup(InlineLevel.ADMIN_MAIN))
                .build();
        methodContainer.getMethodList().add(editMessage);
    }


    public void unknown(MethodContainer methodContainer) {
        log.error("default method");
    }
}
