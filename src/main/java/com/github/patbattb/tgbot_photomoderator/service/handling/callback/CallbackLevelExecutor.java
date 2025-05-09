package com.github.patbattb.tgbot_photomoderator.service.handling.callback;

import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.location.CallbackLocationHandler;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.photo.CallbackVerifyPhotoHandler;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.replymenu.*;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.mainmenu.CallbackMainMenuHandler;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.channel.CallbackChannelHandler;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.controluser.CallbackControlAdminHandler;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.controluser.CallbackControlBanHandler;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.controluser.CallbackControlModeratorHandler;
import com.github.patbattb.tgbot_photomoderator.service.handling.callback.adminmenu.user.CallbackUserHandler;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

@UtilityClass
@Slf4j
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

    public void addAdmin(MethodContainer methodContainer) {
        CallbackAddAdminHandler.process(methodContainer);
    }

    public void addModerator(MethodContainer methodContainer) {
        CallbackAddModeratorHandler.process(methodContainer);
    }

    public void removeAdmin(MethodContainer methodContainer) {
        CallbackDelAdminHandler.process(methodContainer);
    }

    public void removeBan(MethodContainer methodContainer) {
        CallbackDelBanHandler.process(methodContainer);
    }

    public void removeModerator(MethodContainer methodContainer) {
        CallbackDelModeratorHandler.process(methodContainer);
    }

    public static void leaveGroup(MethodContainer methodContainer) {
        CallbackLeaveGroupHandler.process(methodContainer);
    }

    public static void location(MethodContainer methodContainer) {
        CallbackLocationHandler.process(methodContainer);
    }

    public void verifyPhoto(MethodContainer methodContainer) {
        CallbackVerifyPhotoHandler.process(methodContainer);
    }

    public void unknown(MethodContainer methodContainer) {
        log.error("default method");
    }
}
