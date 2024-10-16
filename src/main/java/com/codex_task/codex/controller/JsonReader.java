package com.codex_task.codex.controller;

import com.codex_task.codex.contenhandle.Document;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.List;

@Component
public class JsonReader {

    public List<Document> readContentFromJson(String filePath){
        ObjectMapper mapper = new ObjectMapper();
        try{
            return mapper.readValue(new File(filePath), new TypeReference<List<Document>>() {});
        }catch (IOException e){
            e.printStackTrace();
            return null;
        }
    }
}
