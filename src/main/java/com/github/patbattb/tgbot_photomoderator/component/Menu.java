package com.github.patbattb.tgbot_photomoderator.component;

import lombok.Getter;

import java.util.List;

@Getter
public enum Menu {
    RUN("/run", "Start/resume bot.", List.of(UserGroup.ADMIN)),
    STOP("/stop", "Stop/pause bot.", List.of(UserGroup.ADMIN)),
    ADMIN_PANEL("/admin", "Open admin panel", List.of(UserGroup.ADMIN)),
    LEAVE("/leave", "Leave current group", List.of(UserGroup.ADMIN, UserGroup.MODERATOR));

    private final String name;
    private final String description;
    private final List<UserGroup> scope;

    Menu(String name, String description, List<UserGroup> scope) {
        this.name = name;
        this.description = description;
        this.scope = scope;
    }
}
