package com.github.patbattb.tgbot_photomoderator.component;

import lombok.Data;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;

@Data
public class MethodContainer {
    private final UpdateType type;
    private final Update update;
    private final String chatId;
    private final String userName;
    private final List<BotApiMethod<?>> messageList;
    private final String callbackData;
    private final Integer messageId;

    public MethodContainer(Update update) {
        this.update = update;
        messageList = new ArrayList<>();
        if (update.hasMessage() && update.getMessage().hasText()) {
            this.type = UpdateType.MESSAGE;
            this.chatId = update.getMessage().getChatId().toString();
            this.userName = update.getMessage().getChat().getUserName();
            this.callbackData = null;
            this.messageId = update.getMessage().getMessageId();
        } else if (update.hasCallbackQuery()) {
            this.type = UpdateType.CALLBACK_QUERY;
            this.chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            this.userName = update.getCallbackQuery().getFrom().getUserName();
            this.callbackData = update.getCallbackQuery().getData();
            this.messageId = update.getCallbackQuery().getMessage().getMessageId();
        } else {
            throw new RuntimeException("Unknown message type");
        }
    }
}
