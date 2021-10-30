package com.example.shofna.presentation

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.shofna.R
import com.example.shofna.helper.MyFirebaseMessagingService
import com.example.shofna.helper.PreferenceHelper
import com.example.shofna.helper.ResourceUtil
import com.example.shofna.presentation.homefragment.HomeFragment
import com.example.shofna.presentation.homefragment.HomeFragment.Companion.TAG
import com.example.shofna.presentation.notifications.NotificationResultActivity
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal
import com.google.firebase.messaging.FirebaseMessaging
import com.google.firebase.messaging.RemoteMessage

class MainActivity : AppCompatActivity() {

    companion object {
        const val NOTIFICATION_CHANNEL_ID = "10001"
        const val default_notification_channel_id = "default"
    }

    lateinit var mAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PreferenceHelper(this)

        mAuth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance()
            .subscribeToTopic(PreferenceHelper.getUserGroupId().toString())
        FirebaseMessaging.getInstance().subscribeToTopic("100")



        ResourceUtil().changeLang("ar", this)
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.ttb, 0, 0, 0)
            .replace(R.id.main_frame, HomeFragment()).addToBackStack(null).commit()


        Menus().bottomMenu(this)


    }


}