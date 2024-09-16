package com.github.patbattb.tgbot_photomoderator.service.handling.command;

import com.github.patbattb.tgbot_photomoderator.component.Command;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.command.executor.*;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CommandTypeHandler {

    private final String ERROR = "error";

    private final Map<String, CommandTypeExecutor> COMMAND_TYPE_EXECUTOR_MAP = Map.of(
            Command.RUN.getName(), new CommandTypeExecutorRun(),
            Command.STOP.getName(), new CommandTypeExecutorStop(),
            Command.ADMIN_PANEL.getName(), new CommandTypeExecutorAdminPanel(),
            Command.LEAVE.getName(), new CommandTypeExecutorLeave(),
            ERROR, new CommandTypeExecutorError()

    );
    private final CommandTypeExecutor COMMAND_TYPE_EXECUTOR_DEFAULT = new CommandTypeExecutorDefault();

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
