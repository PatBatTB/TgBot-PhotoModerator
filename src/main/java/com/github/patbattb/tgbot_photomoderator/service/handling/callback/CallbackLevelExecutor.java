package com.github.patbattb.tgbot_photomoderator.service.handling.callback;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.adduser.CallbackAddAdminHandler;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.mainmenu.CallbackMainMenuHandler;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.channel.CallbackChannelHandler;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.controluser.CallbackControlAdminHandler;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.controluser.CallbackControlBanHandler;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.controluser.CallbackControlModeratorHandler;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.user.CallbackUserHandler;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

@UtilityClass
public class CallbackLevelExecutor {

    public void admin(MethodContainer methodContainer) {
        CallbackMainMenuHandler.process(methodContainer);
    }

    public void user(MethodContainer methodContainer) {
        CallbackUserHandler.process(methodContainer);
    }

    public void channel(MethodContainer methodContainer) {
        CallbackChannelHandler.process(methodContainer);
    }

    public void controlAdmin(MethodContainer methodContainer) {
        CallbackControlAdminHandler.process(methodContainer);
    }

    public void controlModerator(MethodContainer methodContainer) {
        CallbackControlModeratorHandler.process(methodContainer);
    }

    public void controlBan(MethodContainer methodContainer) {
        CallbackControlBanHandler.process(methodContainer);
    }

    public static void addAdmin(MethodContainer methodContainer) {
        CallbackAddAdminHandler.process(methodContainer);
    }

    public void unknown(MethodContainer methodContainer) {
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "CallbackLevelExecutor - unknown"));
    }
}
