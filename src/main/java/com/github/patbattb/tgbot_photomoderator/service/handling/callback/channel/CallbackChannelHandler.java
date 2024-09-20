package com.github.patbattb.tgbot_photomoderator.service.handling.callback.channel;

import com.github.patbattb.tgbot_photomoderator.component.InlineButton;
import com.github.patbattb.tgbot_photomoderator.component.MethodContainer;
import com.github.patbattb.tgbot_photomoderator.service.handling.Executable;
import lombok.experimental.UtilityClass;

import java.util.Map;

@UtilityClass
public class CallbackChannelHandler {

    private final Map<String, Executable> callbackChannelExecutorMap = Map.of(
            InlineButton.SET_CHANNEL.getData(), CallbackChannelExecutor::setChannel,
            InlineButton.RETURN.getData(), CallbackChannelExecutor::returning,
            InlineButton.REMOVE_CHANNEL.getData(), CallbackChannelExecutor::removeChannel
    );
    private final Executable callbackChannelExecutorDefault = CallbackChannelExecutor::unknown;

    public static void process(MethodContainer methodContainer) {
        callbackChannelExecutorMap.getOrDefault(methodContainer.getCallbackData().BUTTON(), callbackChannelExecutorDefault)
                .execute(methodContainer);
    }
}
