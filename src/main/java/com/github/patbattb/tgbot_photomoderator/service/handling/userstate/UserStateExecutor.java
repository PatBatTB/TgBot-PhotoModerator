package com.github.patbattb.tgbot_photomoderator.service.handling.userstate;

import com.github.patbattb.tgbot_photomoderator.component.*;
import com.github.patbattb.tgbot_photomoderator.service.markup.KeyboardMarkupProvider;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;

public class UserStateExecutor {
    public static void addAdmin(MethodContainer methodContainer) {
        String userName = User.trimUserName(methodContainer.getMessage().getText());
        SendMessage message = new SendMessage();
        message.setChatId(methodContainer.getChatId());
        if (DataContainer.Container.findInGroup(UserGroup.ADMIN, userName)) {
            message.setText("Пользователь уже в списке администраторов.");
        } else if (DataContainer.Container.addToGroup(UserGroup.ADMIN, userName)) {
            message.setText("Пользователь " + userName + " был успешно добавлен в список администраторов." );
        } else {
            message.setText("""
                    Пользователь не был добавлен.
                    Попросите пользователя написать боту команду "/start" и проверьте правильнось указанного UserName.
                    
                    Хотите заново указать UserName?""");
            message.setReplyMarkup(KeyboardMarkupProvider.getYesNoKeyboardMarkup(InlineLevel.ADD_USER_TO_ADMIN_AGAIN));
        }
        DataContainer.Container.setUserState(methodContainer.getUser().id(), UserState.NO_STATE);
        methodContainer.getMethodList().add(message);
    }

    public static void removeAdmin(MethodContainer methodContainer) {
        String userName = User.trimUserName(methodContainer.getMessage().getText());
        SendMessage message = new SendMessage();
        message.setChatId(methodContainer.getChatId());
        if (!DataContainer.Container.findInGroup(UserGroup.ADMIN, userName)) {
            message.setText("""
                Пользователя нет в списке администраторов.
                Проверьте правильность указанного UserName.
                
                Хотите указать еще раз?
                """);
            message.setReplyMarkup(KeyboardMarkupProvider.getYesNoKeyboardMarkup(InlineLevel.DEL_USER_FROM_ADMIN_AGAIN));
        } else if (DataContainer.Container.removeFromGroup(UserGroup.ADMIN, userName)) {
            message.setText("Пользователь " + userName + " был успешно удален из списка администраторов.");
        } else {
            message.setText("Произошла внутренняя ошибка.\n" +
                    "Пользователь не был удален из списка администраторов.");
        }
        DataContainer.Container.setUserState(methodContainer.getUser().id(), UserState.NO_STATE);
        methodContainer.getMethodList().add(message);
    }

    public static void addModerator(MethodContainer methodContainer) {
        String userName = User.trimUserName(methodContainer.getMessage().getText());
        SendMessage message = new SendMessage();
        message.setChatId(methodContainer.getChatId());
        if (DataContainer.Container.findInGroup(UserGroup.MODERATOR, userName)) {
            message.setText("Пользователь уже в списке модераторов.");
        } else if (DataContainer.Container.addToGroup(UserGroup.MODERATOR, userName)) {
            message.setText("Пользователь " + userName + " был успешно добавлен в список модераторов.");
        } else {
            message.setText("""
                    Пользователь не был добавлен.
                    Попросите пользователя написать боту команду "/start" и проверьте правильнось указанного UserName.
                    
                    Хотите указать еще раз?""");
            message.setReplyMarkup(KeyboardMarkupProvider.getYesNoKeyboardMarkup(InlineLevel.ADD_USER_TO_MODERATOR_AGAIN));
        }
        DataContainer.Container.setUserState(methodContainer.getUser().id(), UserState.NO_STATE);
        methodContainer.getMethodList().add(message);
    }

    public static void removeModerator(MethodContainer methodContainer) {
        String userName = User.trimUserName(methodContainer.getMessage().getText());
        SendMessage message = new SendMessage();
        message.setChatId(methodContainer.getChatId());
        if (!DataContainer.Container.findInGroup(UserGroup.MODERATOR, userName)) {
            message.setText("""
                Пользователя нет в списке модераторов.
                Проверьте правильность указанного UserName.
                
                Хотите указать еще раз?
                """);
            message.setReplyMarkup(KeyboardMarkupProvider.getYesNoKeyboardMarkup(InlineLevel.DEL_USER_FROM_MODERATOR_AGAIN));
        } else if (DataContainer.Container.removeFromGroup(UserGroup.MODERATOR, userName)) {
            message.setText("Пользователь " + userName + " был успешно удален из списка модераторов.");
        } else {
            message.setText("Произошла внутренняя ошибка.\n" +
                    "Пользователь не был удален из списка модераторов.");
        }
        DataContainer.Container.setUserState(methodContainer.getUser().id(), UserState.NO_STATE);
        methodContainer.getMethodList().add(message);
    }

    public static void addBan(MethodContainer methodContainer) {
        String userName = User.trimUserName(methodContainer.getMessage().getText());
        SendMessage message = new SendMessage();
        message.setChatId(methodContainer.getChatId());
        if (DataContainer.Container.findInGroup(UserGroup.BANNED, userName)) {
            message.setText("Данный пользователь уже забанен.");
        } else if (DataContainer.Container.addToGroup(UserGroup.BANNED, userName)) {
            message.setText("Пользователь " + userName + " был забанен.");
        } else {
            //TODO Может не найтись пользователь по юзернейм, но мы не можем попросить пользователя написать боту.
            // нужно пидумать бан по ид пользователя.
            // например предупреждать, что пользователь не найден в списке, хотите забанить пользователя по ИД?
            message.setText("Пользователь не был забанен.");
        }
        DataContainer.Container.setUserState(methodContainer.getUser().id(), UserState.NO_STATE);
        methodContainer.getMethodList().add(message);
    }

    public static void removeBan(MethodContainer methodContainer) {
        String userName = User.trimUserName(methodContainer.getMessage().getText());
        SendMessage message = new SendMessage();
        message.setChatId(methodContainer.getChatId());
        if (!DataContainer.Container.findInGroup(UserGroup.BANNED, userName)) {
            message.setText("Пользователя нет в списке забаненных.");
        } else if (DataContainer.Container.removeFromGroup(UserGroup.BANNED, userName)) {
            message.setText("Пользователь " + userName + " был разбанен.");
        } else {
            message.setText("""
                    Пользователь не был разбанен.
                    Попросите пользователя написать боту команду "/start" и проверьте правильнось указанного UserName.
                    
                    Хотите указать еще раз?""");
            message.setReplyMarkup(KeyboardMarkupProvider.getYesNoKeyboardMarkup(InlineLevel.DEL_USER_FROM_BAN_AGAIN));
        }
        DataContainer.Container.setUserState(methodContainer.getUser().id(), UserState.NO_STATE);
        methodContainer.getMethodList().add(message);
    }

    public static void defaultState(MethodContainer methodContainer) {
        //TODO
        methodContainer.getMethodList().add(new SendMessage(methodContainer.getChatId(), "MessageSate - default"));
    }
}
