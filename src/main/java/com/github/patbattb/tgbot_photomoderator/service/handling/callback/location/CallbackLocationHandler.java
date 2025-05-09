package com.github.patbattb.tgbot_photomoderator.service.handling.callback.location;

import com.github.patbattb.tgbot_photomoderator.component.*;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;

import java.util.Map;

@UtilityClass
@Slf4j
public class CallbackLocationHandler {
    private final Map<String, Executable> LOCATION_MAP = Map.of(
            InlineButton.SET_LOCATION.getData(), CallbackLocationHandler::setLocation,
            InlineButton.RETURN.getData(), CallbackLocationHandler::returning
    );
    private final Executable LOCATION_MAP_DEFAULT = CallbackLocationHandler::unknown;

    public void process(MethodContainer methodContainer) {
        LOCATION_MAP.getOrDefault(methodContainer.getCallbackData().button(), LOCATION_MAP_DEFAULT)
                .execute(methodContainer);
    }

    private void setLocation(MethodContainer methodContainer) {
        DeleteMessage deleteMessage = new DeleteMessage(methodContainer.getChatId(), methodContainer.getMessageId());
        SendMessage sendMessage = new SendMessage(methodContainer.getChatId(), "Введите верхнюю левую координату области.\n" +
                "Координату точки на карте можно скопировать с Яндекс-карт.");
        DataContainer.Container.setUserState(methodContainer.getUser().id(), UserState.SET_FIRST_COORD);
        methodContainer.getMethodList().add(deleteMessage);
        methodContainer.getMethodList().add(sendMessage);
    }

    private void returning(MethodContainer methodContainer) {
        EditMessageText editMessageText = new EditMessageText(AdminPanelTitle.MAIN_TITLE);
        editMessageText.setChatId(methodContainer.getChatId());
        editMessageText.setMessageId(methodContainer.getMessageId());
        editMessageText.setReplyMarkup(KeyboardMarkupProvider.getAdminMainKeyboardMarkup(InlineLevel.ADMIN_MAIN));
        methodContainer.getMethodList().add(editMessageText);
    }

    private void unknown(MethodContainer methodContainer) {
        log.error("default method");
    }
}
