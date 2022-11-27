package com.zancheema.android.pantry.common

import android.view.View
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar

fun View.showSnackbar(message: String) {
    Snackbar
        .make(this, message, BaseTransientBottomBar.LENGTH_LONG)
        .show()
}