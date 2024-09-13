package com.github.patbattb.tgbot_photomoderator.domain;

import com.github.patbattb.tgbot_photomoderator.service.json.JsonHandler;
import lombok.Value;
import org.telegram.telegrambots.meta.api.objects.Update;

@Value
public class DataContainer {
    public static ProgramData programData;
    Update update = null;

    static {
        programData = JsonHandler.loadData();
    }

    public DataContainer() {}
}
