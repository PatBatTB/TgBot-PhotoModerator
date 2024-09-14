package com.github.patbattb.tgbot_photomoderator.service.method;

import com.github.patbattb.tgbot_photomoderator.button.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.component.UpdateType;
import com.github.patbattb.tgbot_photomoderator.service.keyboard.KeyboardProvider;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

import java.util.List;

@UtilityClass
public class MessageParser {

    public static List<BotApiMethod<?>> parse (MethodContainer methodContainer) {
        if (UpdateType.MESSAGE.equals(methodContainer.getType())) {
            System.out.printf("%s (%s)(message): %s\n",
                    methodContainer.getUserName(),
                    methodContainer.getChatId(),
                    methodContainer.getUpdate().getMessage().getText());
            SendMessage answer = new SendMessage();
            answer.setChatId(methodContainer.getChatId());
            answer.setText("menu");
            answer.setReplyMarkup(KeyboardProvider.getYesNoKeyboardMarkup());
            methodContainer.getMessageList().add(answer);
        }
        if (UpdateType.CALLBACK_QUERY.equals(methodContainer.getType())) {
            System.out.printf("%s (%s)(callback): %s\n",
                    methodContainer.getUserName(),
                    methodContainer.getChatId(),
                    methodContainer.getCallbackData());
            EditMessageText newMessage = EditMessageText.builder()
                    .chatId(methodContainer.getChatId())
                    .messageId(methodContainer.getMessageId())
                    .text("error")
                    .build();
            if (InlineButton.YES.getData().equals(methodContainer.getCallbackData())) {
                newMessage.setText("Press \"Confirm\"");
                newMessage.setReplyMarkup(KeyboardProvider.getConfirmKeyboardMarkup());
            } else if (InlineButton.NO.getData().equals(methodContainer.getCallbackData())) {
                newMessage.setText("Canceled.");
            } else if (InlineButton.CONFIRM.getData().equals(methodContainer.getCallbackData())) {
                newMessage.setText("Confirmed");
            }
            methodContainer.getMessageList().add(newMessage);

        }
        return methodContainer.getMessageList();
    }
}
