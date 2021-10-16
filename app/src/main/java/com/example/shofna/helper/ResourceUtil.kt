package com.example.shofna.helper

import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.widget.ImageView
import androidx.core.app.ActivityCompat.startPostponedEnterTransition
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.shofna.R
import com.example.shofna.presentation.MainActivity
import java.util.*

 class ResourceUtil {
    private var myLocale: Locale? = null

     fun getCurrentLanguage(context: Context): String? {

        val langPref = context.packageName + "App_Language"
        val prefs = context.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE)
        return prefs.getString(langPref, "en")


    }


    fun changeLang(lang: String, context: Context) {
        if (lang.equals("", ignoreCase = true)) return
        myLocale = Locale(lang)
        Locale.setDefault(myLocale)
        val config = Configuration()
        config.locale = myLocale
        context.resources.updateConfiguration(config, context.resources.displayMetrics)
        saveLocale(lang, context)


    }

    fun saveLocale(lang: String?, context: Context) {
        val langPref = context.packageName + "App_Language"
        val prefs = context.getSharedPreferences("CommonPrefs", Activity.MODE_PRIVATE)
        val editor = prefs.edit()
        editor.putString(langPref, lang)
        editor.commit()
    }


    fun loudImage(context: Context, imag: ImageView, url: String?) {
        Glide.with(context).applyDefaultRequestOptions(
            RequestOptions()
                .placeholder(R.drawable.noimage)
                .error(R.drawable.noimage)).load(url)


            .into(imag)
    }




}