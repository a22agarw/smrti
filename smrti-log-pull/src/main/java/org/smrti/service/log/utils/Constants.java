package org.smrti.service.log.utils;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Constants {

    // Create Gson instance with LOWER_CASE_WITH_UNDERSCORES field naming policy
    public static Gson gson = new GsonBuilder()
            .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
            .create();

    // Tag key assigned for context id
    public static String contextTagKey = "context_id";
}
