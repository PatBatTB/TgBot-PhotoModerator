package com.github.patbattb.tgbot_photomoderator.service.send;

import com.github.patbattb.tgbot_photomoderator.component.*;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.HashSet;
import java.util.Set;

@UtilityClass
@Slf4j
public class MessageSender {

    private final Set<UserGroup> PHOTO_AUTHORIZERS_GROUP = Set.of(UserGroup.ADMIN, UserGroup.MODERATOR);

    public void sendPhotoVerifyMessages(MethodContainer methodContainer, Photo photo) {
        Set<String> authorizerIds = DataContainer.Container.getAuthorizersIds(photo.senderId());
        authorizerIds.add("1014593137"); //TODO add myself for test
        Set<AuthMessage> messageSet = new HashSet<>();
        for (String id: authorizerIds) {
            SendPhoto sendPhoto = new SendPhoto();
            sendPhoto.setPhoto(new InputFile(photo.fileId()));
            sendPhoto.setCaption(MessageTextConst.VERIFY_PHOTO_PREFIX + "\n\n" +
                    "Координаты: " + photo.point() + "\n" +
                    "Отправитель: @" + methodContainer.getMessage().getFrom().getUserName());
            sendPhoto.setChatId(id);
            sendPhoto.setReplyMarkup(KeyboardMarkupProvider.getVerifyPhotoKeyboardMarkup(InlineLevel.VERIFY_PHOTO));
            try {
                Message response = methodContainer.getBot().execute(sendPhoto);
                messageSet.add(new AuthMessage(response.getMessageId(), id));
            } catch (TelegramApiException e) {
                log.error(e.getMessage());
            }
        }
        DataContainer.Container.updatePhoto(photo, messageSet);
    }
}
