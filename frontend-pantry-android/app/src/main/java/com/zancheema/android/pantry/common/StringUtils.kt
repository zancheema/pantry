package com.zancheema.android.pantry.common

fun anyBlank(vararg strs: String): Boolean {
    return strs.any { s -> s.isBlank() }
}