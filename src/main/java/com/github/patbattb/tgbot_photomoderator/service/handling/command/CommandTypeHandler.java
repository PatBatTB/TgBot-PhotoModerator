package com.github.patbattb.tgbot_photomoderator.service.handling.command;

import com.github.patbattb.tgbot_photomoderator.component.Command;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CommandTypeHandler {

    private final String ERROR = "error";

    private final Map<String, Executable> COMMAND_TYPE_EXECUTOR_MAP = Map.of(
            Command.START.getName(), CommandTypeExecutor::start,
            Command.RUN.getName(), CommandTypeExecutor::run,
            Command.STOP.getName(), CommandTypeExecutor::stop,
            Command.ADMIN_PANEL.getName(), CommandTypeExecutor::adminPanel,
            Command.LEAVE.getName(), CommandTypeExecutor::leave,
            ERROR, CommandTypeExecutor::error

    );
    private final Executable COMMAND_TYPE_EXECUTOR_DEFAULT = CommandTypeExecutor::unknown;

    public void process(MethodContainer methodContainer) {
        COMMAND_TYPE_EXECUTOR_MAP.getOrDefault(getVerifiedCommandName(methodContainer), COMMAND_TYPE_EXECUTOR_DEFAULT)
                .execute(methodContainer);
    }

    private String getVerifiedCommandName(MethodContainer methodContainer) {
        String text = methodContainer.getUpdate().getMessage().getText();
        for (Command command : Command.values()) {
            if (command.getName().equals(text) && !command.getScope().contains(methodContainer.getUserGroup())) {
                return ERROR;
            }
        }
        return text;
    }
}
