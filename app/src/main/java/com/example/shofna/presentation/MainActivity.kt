package com.example.shofna.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shofna.R
import com.example.shofna.helper.PreferenceHelper
import com.example.shofna.helper.ResourceUtil
import com.example.shofna.presentation.homefragment.HomeFragment
import com.google.firebase.FirebaseApp
import com.google.firebase.crashlytics.FirebaseCrashlytics
import com.google.firebase.messaging.FirebaseMessaging
import android.graphics.Color
import android.view.View
import androidx.appcompat.widget.Toolbar

import com.google.android.material.appbar.CollapsingToolbarLayout




class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
       PreferenceHelper(this)

        FirebaseApp.initializeApp(this)
        FirebaseCrashlytics.getInstance()
        FirebaseMessaging.getInstance()

        ResourceUtil().changeLang("ar",this)
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.ttb, 0, 0, 0)
            .replace(R.id.main_frame, HomeFragment()).addToBackStack(null).commit()



        Menus().bottomMenu(this)




    }
}