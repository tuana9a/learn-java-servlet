package com.tuana9a.utils;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

public class JsonUtils {

    private static final JsonUtils instance = new JsonUtils();
    private final Gson gson = new Gson();

    private JsonUtils() {

    }

    public static JsonUtils getInstance() {
        return instance;
    }

    public String toJson(Object o) {
        return gson.toJson(o);
    }

    public JsonElement toJsonElement(Object o) {
        return gson.toJsonTree(o);
    }

    public <T> T fromJson(String json, Class<T> c) {
        return gson.fromJson(json, c);
    }

    public <T> T fromJson(InputStream json, Class<T> c) {
        Reader reader = new InputStreamReader(json, StandardCharsets.UTF_8);
        JsonReader jsonReader = new JsonReader(reader);
        jsonReader.setLenient(true);
        T result = gson.fromJson(jsonReader, c);
        IoUtils.getInstance().close(jsonReader);
        IoUtils.getInstance().close(reader);
        return result;
    }

    public <T> T fromJson(Reader reader, Class<T> c) {
        return gson.fromJson(reader, c);
    }

}
