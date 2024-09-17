package com.github.patbattb.tgbot_photomoderator.component;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AdminPanelTitle {
    public String ADMIN_MAIN_TITLE = "Панель администратора\n  ";
    public String ADMIN_USER_TITLE = ADMIN_MAIN_TITLE + "∟  Управление пользователями\n  ";
    public String ADMIN_CHANNEL_TITLE = ADMIN_MAIN_TITLE + "∟  Управление каналом\n  ";
    public String ADMIN_CONTROL_ADMIN_TITLE = ADMIN_USER_TITLE + "∟  Упаравление администраторами\n  ";
    public String ADMIN_CONTROL_MODERATOR_TITLE = ADMIN_USER_TITLE + "∟  Упаравление модераторами\n  ";
    public String ADMIN_CONTROL_BAN_TITLE = ADMIN_USER_TITLE + "∟  Упаравление банами пользователей\n  ";
}
