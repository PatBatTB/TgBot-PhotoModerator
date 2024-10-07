package com.github.patbattb.tgbot_photomoderator.service.send;

import com.github.patbattb.tgbot_photomoderator.component.DataContainer;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.component.Photo;
import com.github.patbattb.tgbot_photomoderator.component.UserGroup;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Set;

@UtilityClass
@Slf4j
public class MessageSender {

    private final Set<UserGroup> PHOTO_AUTHORIZERS_GROUP = Set.of(UserGroup.ADMIN, UserGroup.MODERATOR);

    public void sendPhotoVerifyMessage(MethodContainer methodContainer, Photo photo) {
        Set<String> authorizerIds = DataContainer.Container.getAuthorizersIds(photo.senderId());
        for (String id: authorizerIds) {
            log.trace("send photo '{}' to id:'{}'", photo.fileId(), id);
        }
            //TODO
    }
}
