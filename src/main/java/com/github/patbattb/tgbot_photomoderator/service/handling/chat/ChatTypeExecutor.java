package com.github.patbattb.tgbot_photomoderator.service.handling.chat;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.update.UpdateTypeHandler;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatAdministrators;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class ChatTypeExecutor {

    public void privateChat(MethodContainer methodContainer) {
        UpdateTypeHandler.process(methodContainer);
    }

    public void groupChat(MethodContainer methodContainer) {
        GetChatAdministrators getAdmins = new GetChatAdministrators(methodContainer.getChatId());

        try {
            List<ChatMember> admins = methodContainer.getBot().execute(getAdmins);
            SendMessage message = new SendMessage(methodContainer.getChatId(), "Admins");
            InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
            List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();
            for (ChatMember admin: admins) {
                if (!admin.getUser().getIsBot()) {
                    InlineKeyboardButton button = InlineKeyboardButton.builder()
                            .text(admin.getUser().getUserName())
                            .callbackData(admin.getUser().getId().toString()).build();
                    List<InlineKeyboardButton> row = List.of(button);
                    keyboard.add(row);
                }
            }
            markup.setKeyboard(keyboard);
            message.setReplyMarkup(markup);
            methodContainer.getBot().execute(message);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }


    }

    public void unknown(MethodContainer methodContainer) {
        //TODO
        //Mock
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "ChatTypeExecutor - unknown"));
    }
}
