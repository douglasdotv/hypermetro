package br.com.dv.metro.util;

import br.com.dv.metro.metrosystem.model.StationDTO;
import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.Map;

public final class JsonParser {

    private static final String IS_NOT_JSON_MESSAGE = "Incorrect file";

    private JsonParser() {
    }

    public static Map<String, Map<String, StationDTO>> parseJsonToMap(String json) {
        Gson gson = new Gson();
        Type type = new TypeToken<Map<String, Map<String, StationDTO>>>(){}.getType();
        try {
            return gson.fromJson(json, type);
        } catch (JsonParseException ex) {
            throw new JsonParseException(IS_NOT_JSON_MESSAGE, ex);
        }
    }

}
