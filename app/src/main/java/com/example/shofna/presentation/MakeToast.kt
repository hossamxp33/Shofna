package com.example.shofna.presentation

import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.content.res.ResourcesCompat
import com.example.shofna.R

import www.sanju.motiontoast.MotionToast

class MakeToast {


    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    fun ERROR_MotionToast_main(massage: String, context: Activity) {
        MotionToast.createColorToast(
            context,
            "Error",
            massage,
            MotionToast.TOAST_ERROR,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, R.font.kokn)
        )
    }

    fun SUCCESS_MotionToast(massage: String, context: Context) {
        MotionToast.createColorToast(
            context as Activity,
            "success 😍",
            massage,
            MotionToast.TOAST_SUCCESS,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, R.font.helvetica_regular)
        )
    }

    fun Warning_MotionToast(massage: String, context: Activity) {
        MotionToast.createColorToast(
            context,
            "Warning",
            massage,
            MotionToast.TOAST_WARNING,
            MotionToast.GRAVITY_BOTTOM,
            MotionToast.LONG_DURATION,
            ResourcesCompat.getFont(context, R.font.helvetica_regular)
        )
    }

}