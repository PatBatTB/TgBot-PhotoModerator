package com.github.patbattb.tgbot_photomoderator.service.handling.callback;

import com.github.patbattb.tgbot_photomoderator.component.CallBackData;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CallBackParser {
    public CallBackData parse(String callbackString) {
        String[] parsedString = callbackString.split("_");
        return new CallBackData(parsedString[0], parsedString[1]);
    }

    public String build(String level, String button) {
        return level + "_" + button;
    }
}
