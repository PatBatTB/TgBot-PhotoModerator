package com.github.patbattb.tgbot_photomoderator.service.handling.callback.executor.admin.main;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import lombok.Value;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.DeleteMessage;

@Value
public class CallbackAdminExecutorClose implements CallbackAdminExecutor {
    @Override
    public void execute(MethodContainer methodContainer) {
        DeleteMessage delete = new DeleteMessage(methodContainer.getChatId(), methodContainer.getMessageId());
        methodContainer.getMethodList().add(delete);
    }
}
