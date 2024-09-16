package com.github.patbattb.tgbot_photomoderator.component;
import com.github.patbattb.tgbot_photomoderator.service.handling.method.MethodHandler;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

@Value
@EqualsAndHashCode(callSuper=true)
public class TgBot extends TelegramLongPollingBot {

    String botUsername;

    public TgBot (String botUsername, String botToken) {
        super(botToken);
        this.botUsername = botUsername;
        Props.init();
    }

    @Override
    public void onUpdateReceived(Update update) {
        List <BotApiMethod<?>> methodList = MethodHandler.process(new MethodContainer(update));
        try {
            for (BotApiMethod<?> method: methodList) {
                execute(method);
            }
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
