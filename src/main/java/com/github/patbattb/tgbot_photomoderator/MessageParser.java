package com.github.patbattb.tgbot_photomoderator;

import lombok.Value;
import lombok.extern.java.Log;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;

@Value
@Log
public class MessageParser {
    List<BotApiMethod<?>> messageList = new ArrayList<>();

    List<BotApiMethod<?>> parse (Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String chatId = update.getMessage().getChatId().toString();
            SendMessage message = new SendMessage();
            message.setChatId(chatId);
            message.setText("menu");
            message.setReplyMarkup(KeyboardProvider.getYesNoKeyboardMarkup());
            messageList.add(message);
        }
        if (update.hasCallbackQuery()) {
            String chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            Integer messageId = update.getCallbackQuery().getMessage().getMessageId();
            String data = update.getCallbackQuery().getData();
            EditMessageText newMessage = EditMessageText.builder()
                    .chatId(chatId)
                    .messageId(messageId)
                    .text("error")
                    .build();
            if ("Yes".equals(data)) {
                newMessage.setText("Press \"Confirm\"");
                newMessage.setReplyMarkup(KeyboardProvider.getConfirmKeyboardMarkup());
            } else if ("No".equals(data)) {
                newMessage.setText("Canceled.");
            } else if ("Confirm".equals(data)) {
                newMessage.setText("Confirmed");
            }
            messageList.add(newMessage);

        }
        return messageList;
    }
}
