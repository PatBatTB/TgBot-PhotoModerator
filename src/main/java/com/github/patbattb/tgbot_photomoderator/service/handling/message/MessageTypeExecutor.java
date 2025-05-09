package com.github.patbattb.tgbot_photomoderator.service.handling.message;

import com.github.patbattb.tgbot_photomoderator.component.*;
import com.github.patbattb.tgbot_photomoderator.service.handling.command.CommandTypeHandler;
import com.github.patbattb.tgbot_photomoderator.service.handling.userstate.UserStateHandler;
import com.github.patbattb.tgbot_photomoderator.service.send.MessageSender;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.groupadministration.ExportChatInviteLink;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.time.LocalDateTime;
import java.util.Comparator;

@UtilityClass
@Slf4j
public class MessageTypeExecutor {

    public void text(MethodContainer methodContainer) {
        UserStateHandler.process(methodContainer);
    }

    public void command(MethodContainer methodContainer) {
        CommandTypeHandler.process(methodContainer);
    }

    public void photo(MethodContainer methodContainer) {
        SendMessage message = new SendMessage();
        message.setChatId(methodContainer.getChatId());
        if (UserGroup.BANNED.equals(methodContainer.getUserGroup())) {
            try {
                String inviteLink = methodContainer.getBot().execute(new ExportChatInviteLink(methodContainer.getChatId()));
                message.setText("К сожалению фотографии от вас не принимаются. Свяжитесь с администратором канала.\n" +
                        inviteLink);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
        else if (methodContainer.getMessage().getCaption() == null) {
            message.setText("У фотографии отсутствуют координаты.\n" +
                    "В описании к фотографии необходимо указать координаты места, где было сделано фото. Координаты места можно скопировать с Яндекс-карт. " +
                    "Попробуйте отправить фото снова с указанием координат.");
        } else if (!DataContainer.Container.checkoutPoint(Point.get(methodContainer.getMessage().getCaption()))) {
            message.setText("Фотографии публикуются только из указанной местности. Судя по координатам фото сделано вне разрешенной области.");
        }
        else {
            Photo photo = new Photo(methodContainer.getPhotoId(),
                    PhotoStatus.APPROVAL_WAITING,
                    Point.get(methodContainer.getMessage().getCaption()),
                    LocalDateTime.now(),
                    methodContainer.getUser().id(), null);
            DataContainer.Container.addNewPhoto(photo);
            MessageSender.sendPhotoVerifyMessages(methodContainer, photo);
            message.setText("Ваше фото отправлено на модерацию. После одобрения фотографии одним из модераторов, фото будет опубликовано на канале.");
        }
        methodContainer.getMethodList().add(message);
    }

    public void unknown(MethodContainer methodContainer) {
        log.error("default method");
    }
}
