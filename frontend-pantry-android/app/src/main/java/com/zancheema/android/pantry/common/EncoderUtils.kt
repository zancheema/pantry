package com.zancheema.android.pantry.common

import java.nio.charset.StandardCharsets
import java.util.*

object EncoderUtils {
    fun getBasicAuthHeader(username: String, password: String): String {
        val valueToEncode = "$username:$password"
        val encodedValue = Base64
            .getEncoder()
            .encodeToString(valueToEncode.toByteArray(StandardCharsets.UTF_8))
        return "Basic $encodedValue";
    }
}