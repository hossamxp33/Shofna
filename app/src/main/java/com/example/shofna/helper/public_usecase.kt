package com.example.shofna.helper

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.shofna.R
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.registerActivity.RegisterActivity
import www.sanju.motiontoast.MotionToast
import java.lang.Long
import java.text.SimpleDateFormat
import java.util.*

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

@BindingAdapter("app:datetext")
fun setDatetext(text: TextView, resource: String?) {

    val myFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")

    val dateObj: Date

    dateObj = myFormat.parse(resource)
    val timestamp = dateObj.getTime().toString()//  //Example -> in ms
    val fromServer = SimpleDateFormat("yyyy-MM-dd HH:mm a", Locale("en"))
    val dateString = fromServer.format(Date(Long.parseLong(timestamp)))


    text.text = dateString

}

@BindingAdapter("app:imageStock")

fun setimageStock(imageView: AppCompatImageView, resource: String?) {

}

@BindingAdapter("app:imageResource")
fun setImageResource(imageView: AppCompatImageView, resource: String?) {
    Glide.with(imageView.context).load(resource).into(imageView)
}
fun checkUserLogin(context: Context): Boolean {
    if (!PreferenceHelper.getToken().isNullOrEmpty() )
        return true
    else

        Toast.makeText(context,"يجب تسجيل الدخول اولا", Toast.LENGTH_SHORT).show()
        return  false



}
