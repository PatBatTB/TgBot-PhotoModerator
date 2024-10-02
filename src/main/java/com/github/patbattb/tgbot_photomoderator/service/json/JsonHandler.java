package com.github.patbattb.tgbot_photomoderator.service.json;

import com.github.patbattb.tgbot_photomoderator.component.DataContainer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.experimental.UtilityClass;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.lang.reflect.Modifier;
import java.nio.file.Files;
import java.nio.file.Path;

@UtilityClass
public class JsonHandler {
    final Path PROPS_DIR_PATH = Path.of("props");
    final Path JSON_PATH = Path.of("props/props.json");
    final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithModifiers(Modifier.ABSTRACT)
            .create();

    public void loadData() {
        if (!Files.exists(PROPS_DIR_PATH) || !Files.isDirectory(PROPS_DIR_PATH)) {
            try {
                Files.createDirectory(PROPS_DIR_PATH);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        if (!Files.exists(JSON_PATH)) saveData();
        try(Reader reader = Files.newBufferedReader(JSON_PATH)) {
            DataContainer.setContainer(GSON.fromJson(reader, DataContainer.Container.class));
            if (DataContainer.Container.fixIncorrectUserState()) saveData();
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    public void saveData() {
        try(Writer writer = Files.newBufferedWriter(JSON_PATH)) {
            GSON.toJson(DataContainer.getContainer(), writer);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
