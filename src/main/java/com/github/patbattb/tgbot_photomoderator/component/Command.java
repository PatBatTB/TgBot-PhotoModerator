package com.github.patbattb.tgbot_photomoderator.component;

import lombok.Getter;

import java.util.List;

@Getter
public enum Command {
    RUN("/run", "Start/resume bot.", true, List.of(UserGroup.ADMIN)),
    START("/start", "Initial command", false, List.of(UserGroup.values())),
    SET_CHANNEL("/setchannel", "Set this channel to publications", false, List.of(UserGroup.ADMIN)),
    STOP("/stop", "Stop/pause bot.", true, List.of(UserGroup.ADMIN)),
    ADMIN_PANEL("/control", "Open admin control panel", true, List.of(UserGroup.ADMIN)),
    LEAVE("/leave", "Leave current group", true, List.of(UserGroup.ADMIN, UserGroup.MODERATOR));

    private final String name;
    private final String description;
    private final boolean menuCommand;
    private final List<UserGroup> scope;

    Command(String name, String description, boolean menuCommand, List<UserGroup> scope) {
        this.name = name;
        this.description = description;
        this.menuCommand = menuCommand;
        this.scope = scope;
    }
}
