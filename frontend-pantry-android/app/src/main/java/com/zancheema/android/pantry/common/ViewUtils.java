package com.zancheema.android.pantry.common;

import android.view.View;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class ViewUtils {
    public static void showSnackbar(View view, String message) {
        Snackbar
                .make(view, message, BaseTransientBottomBar.LENGTH_LONG)
                .show();
    }
}
