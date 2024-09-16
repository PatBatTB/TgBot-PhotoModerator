package com.github.patbattb.tgbot_photomoderator.service.menu;

import com.github.patbattb.tgbot_photomoderator.component.Command;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.methods.BotApiMethod;
import org.telegram.telegrambots.meta.api.methods.commands.DeleteMyCommands;
import org.telegram.telegrambots.meta.api.methods.commands.SetMyCommands;
import org.telegram.telegrambots.meta.api.objects.commands.BotCommand;
import org.telegram.telegrambots.meta.api.objects.commands.scope.BotCommandScopeChat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@UtilityClass
public class MenuProvider {

    public static BotApiMethod<?> getGroupMenu(MethodContainer methodContainer) {
        List<BotCommand> botCommandList = new ArrayList<>();
        List<Command> commandList = Arrays.stream(Command.values())
                .filter(Command::isMenuCommand)
                .filter(elem -> elem.getScope().contains(methodContainer.getUserGroup()))
                .toList();
        commandList.forEach(elem -> botCommandList.add(new BotCommand(elem.getName(), elem.getDescription())));
        BotCommandScopeChat scope = new BotCommandScopeChat(methodContainer.getChatId());
        return (botCommandList.isEmpty()) ?
            new DeleteMyCommands(scope, null) :
            new SetMyCommands(botCommandList, scope, null);
    }
}
