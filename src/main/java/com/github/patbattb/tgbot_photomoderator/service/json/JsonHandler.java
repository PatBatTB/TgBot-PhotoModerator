package com.github.patbattb.tgbot_photomoderator.service.json;

import com.github.patbattb.tgbot_photomoderator.domain.ProgramData;
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
    final Path file = Path.of("props/props.json");
    final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .excludeFieldsWithModifiers(Modifier.ABSTRACT)
            .create();
    ProgramData programData = new ProgramData();
    public ProgramData loadData() {
        if (!Files.exists(file)) saveData();
        try(Reader reader = Files.newBufferedReader(file)) {
            programData = gson.fromJson(reader, ProgramData.class);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return programData;
    }

    public void saveData() {
        try(Writer writer = Files.newBufferedWriter(file)) {
            gson.toJson(programData, writer);
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
