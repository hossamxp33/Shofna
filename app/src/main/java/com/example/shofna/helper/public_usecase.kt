package com.example.shofna.helper

import android.app.Activity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.shofna.R
import www.sanju.motiontoast.MotionToast

///TOAST_SUCCESS_MotionToast
fun SUCCESS_MotionToast(massage: String, context: Activity) {
    MotionToast.createColorToast(
        context,
    "downloaded!  😍",
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
@BindingAdapter("app:imageStock")

fun setimageStock(imageView: AppCompatImageView, resource: String?) {

}

@BindingAdapter("app:imageResource")
fun setImageResource(imageView: AppCompatImageView, resource: String?) {
    Glide.with(imageView.context).load(resource).into(imageView)
}

