package com.github.patbattb.tgbot_photomoderator.service.handling.userstate;

import com.github.patbattb.tgbot_photomoderator.component.*;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class UserStateExecutor {

    public static void addAdmin(MethodContainer methodContainer) {
        String userName = methodContainer.getMessage().getText();
        SendMessage message = new SendMessage();
        message.setChatId(methodContainer.getChatId());
        if (DataContainer.Container.findUser(UserGroup.ADMIN, userName)) {
            message.setText("Пользователь уже в списке администраторов.");
        } else if (DataContainer.Container.addUser(UserGroup.ADMIN, userName)) {
            message.setText("Пользователь " + userName + " был успешно добавлен в список администраторов." );
        } else {
            message.setText("""
                    Пользователь не был добавлен.
                    Попросите пользователя написать боту команду "/start" и проверьте правильнось указанного UserName (его необходимо написать без "@").
                    
                    Хотите заново указать UserName?""");
            message.setReplyMarkup(KeyboardMarkupProvider.getYesNoKeyboardMarkup(InlineLevel.ADD_USER_TO_ADMIN.getName()));
        }
        DataContainer.Container.setUserChatState(methodContainer.getUser().id(), ChatState.NOSTATE);
        methodContainer.getMethodList().add(message);
    }

    public static void defaultState(MethodContainer methodContainer) {
        //TODO
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "MessageSate - default"));
    }
}
