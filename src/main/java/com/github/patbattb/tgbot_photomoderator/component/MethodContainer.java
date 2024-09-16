package com.github.patbattb.tgbot_photomoderator.component;

import com.github.patbattb.tgbot_photomoderator.service.handling.callback.CallBackParser;
import com.github.patbattb.tgbot_photomoderator.service.handling.update.UpdateType;
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
    private final List<BotApiMethod<?>> methodList;
    private final CallBackData callbackData;
    private final Integer messageId;
    private final UserGroup userGroup;

    public MethodContainer(Update update) {
        this.update = update;
        this.methodList = new ArrayList<>();
        if (update.hasMessage()) {
            this.type = UpdateType.MESSAGE;
            this.chatId = update.getMessage().getChatId().toString();
            this.userName = update.getMessage().getChat().getUserName();
            this.callbackData = null;
            this.messageId = update.getMessage().getMessageId();
        } else if (update.hasCallbackQuery()) {
            this.type = UpdateType.CALLBACK_QUERY;
            this.chatId = update.getCallbackQuery().getMessage().getChatId().toString();
            this.userName = update.getCallbackQuery().getFrom().getUserName();
            this.callbackData = CallBackParser.parse(update.getCallbackQuery().getData());
            this.messageId = update.getCallbackQuery().getMessage().getMessageId();
        } else {
            throw new RuntimeException("Unknown message type");
        }
        this.userGroup = Props.Container.getUserGroup(this.chatId);
    }
}
