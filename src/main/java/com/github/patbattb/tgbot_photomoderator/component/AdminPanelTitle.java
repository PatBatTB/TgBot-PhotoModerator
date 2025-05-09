package com.github.patbattb.tgbot_photomoderator.component;

import lombok.experimental.UtilityClass;

@UtilityClass
public class AdminPanelTitle {
    public String MAIN_TITLE = "Панель администратора\n  ";
    public String USER_TITLE = MAIN_TITLE + "∟  Управление пользователями\n  ";
    public String CHANNEL_TITLE = MAIN_TITLE + "∟  Управление каналом\n  ";
    public String CONTROL_ADMIN_TITLE = USER_TITLE + "∟  Упаравление администраторами\n  ";
    public String CONTROL_MODERATOR_TITLE = USER_TITLE + "∟  Упаравление модераторами\n  ";
    public String CONTROL_BAN_TITLE = USER_TITLE + "∟  Упаравление банами пользователей\n  ";
    public String LOCATION_TITLE = MAIN_TITLE + "∟  Упаравление локацией\n  ";
}
