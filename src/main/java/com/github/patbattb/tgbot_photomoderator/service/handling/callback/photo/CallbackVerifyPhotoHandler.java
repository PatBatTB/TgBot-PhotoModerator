package com.github.patbattb.tgbot_photomoderator.service.handling.callback.photo;

import com.github.patbattb.tgbot_photomoderator.component.*;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageCaption;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@UtilityClass
@Slf4j
public class CallbackVerifyPhotoHandler {
    private final Map<String, Executable> VERIFY_PHOTO_MAP = Map.of(
            InlineButton.VERIFY_PHOTO.getData(), CallbackVerifyPhotoHandler::postPhoto
    );
    private final Executable VERIFY_PHOTO_DEFAULT = CallbackVerifyPhotoHandler::unknown;

    public void process(MethodContainer methodContainer) {
        VERIFY_PHOTO_MAP.getOrDefault(methodContainer.getCallbackData().button(), VERIFY_PHOTO_DEFAULT)
                .execute(methodContainer);
    }

    private void postPhoto(MethodContainer methodContainer) {
        sendPhotoToChannel(methodContainer);
        editSendedMessages(methodContainer);
        //TODO change photoStatus and delete VerifyMessages block
    }

    private void sendPhotoToChannel(MethodContainer methodContainer) {
        SendPhoto sendPhoto = new SendPhoto(DataContainer.Container.getChannel(),
                new InputFile(methodContainer.getPhotoId()));
        sendPhoto.setCaption(getCaptionForChannel(methodContainer.getMessage().getCaption()));
        try {
            methodContainer.getBot().execute(sendPhoto);
        } catch (TelegramApiException e) {
            log.error(e.getMessage());
        }
    }

    private void editSendedMessages(MethodContainer methodContainer) {
        String caption = getCaptionForChannel(methodContainer.getMessage().getCaption()) +
                "\nОдобрил: @" + methodContainer.getUser().userName();
        Set<AuthMessage> messageSet = DataContainer.Container.getAuthMessageSet(methodContainer.getPhotoId());
        for (AuthMessage mes: messageSet) {
            EditMessageCaption editMessage = new EditMessageCaption();
            editMessage.setCaption(caption);
            editMessage.setMessageId(mes.messageId());
            editMessage.setChatId(mes.authorizerId());
            try {
                methodContainer.getBot().execute(editMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private String getCaptionForChannel(String moderatorCaption) {
        String[] lines = moderatorCaption.split("\\n+");
        return Arrays.stream(lines).filter(elem -> !MessageTextConst.VERIFY_PHOTO_PREFIX.equals(elem))
                .collect(Collectors.joining("\n"));
    }

    private void unknown(MethodContainer methodContainer) {
        log.error("default method");
    }
}
