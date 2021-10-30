package com.example.shofna.presentation.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.shofna.R
import com.example.shofna.databinding.NotificationAcitivtyBinding
import com.example.shofna.helper.PreferenceHelper
import com.example.shofna.helper.ResourceUtil
import com.example.shofna.helper.setDatetext
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.news_details_fragment.*
import kotlinx.android.synthetic.main.notification_acitivty.*
import kotlinx.android.synthetic.main.notification_acitivty.news_details_image

class NotificationResultActivity : AppCompatActivity() {
    companion object {
        const val NOTIFICATION_CHANNEL_ID = "10001"
        const val default_notification_channel_id = "default"
    }
    val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }


    var id  = 0

    lateinit var mAuth: FirebaseAuth
    lateinit var view: NotificationAcitivtyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        view = DataBindingUtil.setContentView(this, R.layout.notification_acitivty);

        PreferenceHelper(this)
        mAuth = FirebaseAuth.getInstance()
        FirebaseApp.initializeApp(this)
        FirebaseMessaging.getInstance()
        .subscribeToTopic(PreferenceHelper.getUserGroupId().toString())
        FirebaseMessaging.getInstance().subscribeToTopic("100")
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val importance = NotificationManager.IMPORTANCE_HIGH

            val notificationChannel = NotificationChannel(
                MainActivity.NOTIFICATION_CHANNEL_ID,
                "NOTIFICATION_CHANNEL_NAME", importance
            )
            notificationChannel.description = ""

            val mNotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            assert(mNotificationManager != null)
            mNotificationManager.createNotificationChannel(notificationChannel)
        }



        val extras = intent.getExtras();

        if (extras?.getInt("companyUserid") != null) {

            id = extras.getInt("companyUserid")!!



        }
        viewModel.OpenNotifications(id)

     viewModel.oPenNotificationLD?.observe(this, Observer {

         ResourceUtil().loudImage(this, news_details_image, it.data.photo)
         view.newsTitle.text = it.data.text
         view.description.text = it.data.description
         view. created.text= it.data.created
         view.progress.visibility = View.GONE

     })


    }



}