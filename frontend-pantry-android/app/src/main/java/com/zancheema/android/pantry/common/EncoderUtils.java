package com.zancheema.android.pantry.common;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class EncoderUtils {
    public static String getBasicAuthHeader(String username, String password) {
        String valueToEncode = username + ":" + password;
        String encodedValue = Base64
                .getEncoder()
                .encodeToString(valueToEncode.getBytes(StandardCharsets.UTF_8));
        return "Basic " + encodedValue;
    }
}
