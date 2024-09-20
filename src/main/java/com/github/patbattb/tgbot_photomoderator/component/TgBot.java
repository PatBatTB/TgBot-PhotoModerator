package com.github.patbattb.tgbot_photomoderator.component;
import com.github.patbattb.tgbot_photomoderator.service.handling.method.MethodHandler;
import lombok.EqualsAndHashCode;
import lombok.Value;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;


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
        MethodHandler.process(new MethodContainer(update, this));
    }

    @Override
    public String getBotUsername() {
        return botUsername;
    }
}
