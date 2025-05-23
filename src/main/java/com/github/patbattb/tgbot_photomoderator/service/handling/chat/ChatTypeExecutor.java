package com.github.patbattb.tgbot_photomoderator.service.handling.chat;

import com.github.patbattb.tgbot_photomoderator.component.Command;
import com.github.patbattb.tgbot_photomoderator.component.DataContainer;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.component.UserGroup;
import com.github.patbattb.tgbot_photomoderator.service.handling.update.UpdateTypeHandler;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.groupadministration.ExportChatInviteLink;
import org.telegram.telegrambots.meta.api.methods.groupadministration.GetChatAdministrators;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.chatmember.ChatMember;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;
import java.util.Optional;

@UtilityClass
@Slf4j
public class ChatTypeExecutor {

    public void privateChat(MethodContainer methodContainer) {
        UpdateTypeHandler.process(methodContainer);
    }

    public void groupChat(MethodContainer methodContainer) {
        if (UserGroup.ADMIN.equals(methodContainer.getUserGroup()) &&
                methodContainer.getUpdate().hasMessage() &&
                methodContainer.getMessage().hasText() &&
                Command.SET_CHANNEL.getName().equals(methodContainer.getMessage().getText())) {
            GetChatAdministrators getChatAdministrators = new GetChatAdministrators(methodContainer.getChatId());
            SendMessage sendMessage = new SendMessage();
            sendMessage.setChatId(methodContainer.getUser().id());
            try {
                List<ChatMember> list = methodContainer.getBot().execute(getChatAdministrators);
                Optional<ChatMember> optional = list.stream()
                        .filter(elem -> methodContainer.getUser().id().equals(String.valueOf(elem.getUser().getId())))
                        .findAny();
                if (optional.isEmpty()) sendMessage.setText("Для добавления канала вы должны быть его администратором или создателем.");
                else {
                    DataContainer.Container.setChannel(methodContainer.getChatId());
                    String inviteLink = methodContainer.getBot().execute(new ExportChatInviteLink(methodContainer.getChatId()));
                    sendMessage.setChatId(methodContainer.getUser().id());
                    sendMessage.setText("Канал для публикаций задан.\n" +
                            "Теперь бот будет публиковать фотографии на этом канале\n" +
                            inviteLink);
                }
                methodContainer.getMethodList().add(sendMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void unknown(MethodContainer methodContainer) {
        log.debug("unknown method");
    }
}
