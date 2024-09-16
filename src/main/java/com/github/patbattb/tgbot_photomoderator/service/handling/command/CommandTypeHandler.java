package com.github.patbattb.tgbot_photomoderator.service.handling.command;

import com.github.patbattb.tgbot_photomoderator.component.Menu;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.command.executor.*;
import lombok.experimental.UtilityClass;
import org.telegram.telegrambots.meta.api.objects.Message;

import java.util.Map;

@UtilityClass
public class CommandTypeHandler {

    private final Map<String, CommandTypeExecutor> COMMAND_TYPE_EXECUTOR_MAP = Map.of(
            Menu.RUN.getName(), new CommandTypeExecutorRun(),
            Menu.STOP.getName(), new CommandTypeExecutorStop(),
            Menu.ADMIN_PANEL.getName(), new CommandTypeExecutorAdminPanel(),
            Menu.LEAVE.getName(), new CommandTypeExecutorLeave()
    );
    private final CommandTypeExecutor COMMAND_TYPE_EXECUTOR_DEFAULT = new CommandTypeExecutorDefault();

    public void process(MethodContainer methodContainer) {
        Message message = methodContainer.getUpdate().getMessage();
        COMMAND_TYPE_EXECUTOR_MAP.getOrDefault(message.getText(), COMMAND_TYPE_EXECUTOR_DEFAULT)
                .execute(methodContainer);
    }
}
