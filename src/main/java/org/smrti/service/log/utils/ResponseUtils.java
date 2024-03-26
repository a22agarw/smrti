package org.smrti.service.log.utils;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class ResponseUtils {

    public static JsonObject error(String msg) {

        JsonObject response = new JsonObject();
        response.addProperty("status", false);
        response.add("data", new JsonObject());
        response.addProperty("error", msg);
        return response;
    }

    public static JsonObject error(String msg, JsonObject data) {
        JsonObject response = new JsonObject();
        response.addProperty("status", false);
        response.add("data", data);
        response.addProperty("error", msg);
        return response;
    }

    public static JsonObject success() {

        JsonObject response = new JsonObject();
        response.addProperty("status", true);
        response.add("data", new JsonObject());
        response.addProperty("error", "");
        return response;
    }

    public static JsonObject success(String msg) {

        JsonObject response = new JsonObject();
        response.addProperty("status", true);
        response.addProperty("data", msg);
        response.addProperty("error", "");
        return response;
    }

    public static JsonObject success(JsonObject data) {

        JsonObject response = new JsonObject();
        response.addProperty("status", true);
        response.add("data", data);
        response.addProperty("error", "");
        return response;
    }

    public static JsonObject success(JsonArray data) {

        JsonObject response = new JsonObject();
        response.addProperty("status", true);
        response.add("data", data);
        response.addProperty("error", "");
        return response;
    }

}
