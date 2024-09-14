package com.github.patbattb.tgbot_photomoderator.service.method;

import com.github.patbattb.tgbot_photomoderator.button.InlineButton;
import com.github.patbattb.tgbot_photomoderator.domain.Props;
import com.github.patbattb.tgbot_photomoderator.domain.UserGroup;
import com.github.patbattb.tgbot_photomoderator.service.json.JsonHandler;
import com.github.patbattb.tgbot_photomoderator.service.keyboard.KeyboardProvider;
import lombok.Value;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Value
public class MessageParser {
    List<BotApiMethod<?>> messageList = new ArrayList<>();

    public List<BotApiMethod<?>> parse (Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            Message message = update.getMessage();
            String chatId = message.getChatId().toString();
            String userName = message.getChat().getUserName();
            System.out.printf("%s (%s)(message): %s\n", userName, chatId, message.getText());
            SendMessage answer = new SendMessage();
            answer.setChatId(chatId);
            answer.setText("menu");
            answer.setReplyMarkup(KeyboardProvider.getYesNoKeyboardMarkup());
            messageList.add(answer);
            Props.Container.getUsers().get(UserGroup.ADMIN).add(chatId);
            JsonHandler.saveData();
        }
        if (update.hasCallbackQuery()) {
            CallbackQuery callbackQuery = update.getCallbackQuery();
            String chatId = callbackQuery.getMessage().getChatId().toString();
            Integer messageId = callbackQuery.getMessage().getMessageId();
            String data = callbackQuery.getData();
            String userName = callbackQuery.getFrom().getUserName();
            System.out.printf("%s (%s)(callback): %s\n", userName, chatId, data);
            EditMessageText newMessage = EditMessageText.builder()
                    .chatId(chatId)
                    .messageId(messageId)
                    .text("error")
                    .build();
            if (InlineButton.YES.getData().equals(data)) {
                newMessage.setText("Press \"Confirm\"");
                newMessage.setReplyMarkup(KeyboardProvider.getConfirmKeyboardMarkup());
            } else if (InlineButton.NO.getData().equals(data)) {
                newMessage.setText("Canceled.");
            } else if (InlineButton.CONFIRM.getData().equals(data)) {
                newMessage.setText("Confirmed");
            }
            messageList.add(newMessage);

        }
        return messageList;
    }
}
