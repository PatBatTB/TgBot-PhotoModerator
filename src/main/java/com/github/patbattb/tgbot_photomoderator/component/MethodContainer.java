package com.github.patbattb.tgbot_photomoderator.component;

import com.github.patbattb.tgbot_photomoderator.service.handling.callback.CallBackParser;
import com.github.patbattb.tgbot_photomoderator.service.handling.update.UpdateType;
import lombok.Data;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Data
public final class MethodContainer {
    private final UpdateType type;
    private final Update update;
    private final String chatId;
    private final User user;
    private final List<BotApiMethod<?>> methodList;
    private final CallBackData callbackData;
    private final Integer messageId;
    private final UserGroup userGroup;
    private final String chatType;
    private final TgBot bot;
    private final Message message;
    private final String photoId;

    public MethodContainer(Update update, TgBot bot) {
        this.bot = bot;
        this.update = update;
        this.methodList = new ArrayList<>();
        if (update.hasMessage()) {
            this.type = UpdateType.MESSAGE;
            this.message = update.getMessage();
            this.chatId = this.message.getChatId().toString();
            this.user = new User(message.getFrom().getId().toString(),
                    message.getFrom().getFirstName(),
                    message.getFrom().getLastName(),
                    message.getFrom().getUserName(),
                    DataContainer.Container.getChatState(this.chatId));
            this.callbackData = null;
            this.messageId = this.message.getMessageId();
            this.chatType = this.message.getChat().getType();
        } else if (update.hasCallbackQuery()) {
            this.message = (Message) update.getCallbackQuery().getMessage();
            this.type = UpdateType.CALLBACK_QUERY;
            this.chatId = message.getChatId().toString();
            this.user = new User(update.getCallbackQuery().getFrom().getId().toString(),
                    update.getCallbackQuery().getFrom().getFirstName(),
                    update.getCallbackQuery().getFrom().getLastName(),
                    update.getCallbackQuery().getFrom().getUserName(),
                    DataContainer.Container.getChatState(this.chatId));
            this.callbackData = CallBackParser.parse(update.getCallbackQuery().getData());
            this.messageId = message.getMessageId();
            this.chatType = message.getChat().getType();
        } else {
            throw new RuntimeException("Unknown message type");
        }
        this.userGroup = DataContainer.Container.getUserGroup(this.user.id());
        this.photoId = setPhotoId();
    }

    private String setPhotoId() {
        Optional<PhotoSize> optional = message.getPhoto().stream().max(Comparator.comparingInt(PhotoSize::getFileSize));
        return optional.map(PhotoSize::getFileId).orElse("");
    }
}
