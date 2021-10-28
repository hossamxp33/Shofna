package com.example.shofna.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.shofna.R

import com.example.shofna.presentation.notifications.NotificationResultActivity


class SplashActivity : AppCompatActivity() {

    private var mDelayHandler: Handler? = null
    private val SPLASH_DELAY: Long = 3000 //3 seconds
    private var intent_type = ""
    private var id = ""

    internal val mRunnable: Runnable = Runnable {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        //Initialize the Handler
        mDelayHandler = Handler()

        //Navigate with delay
        mDelayHandler!!.postDelayed(mRunnable, SPLASH_DELAY)

        onNewIntent(getIntent());

        if (!isFinishing) {
            if (id == "") {
                val intent = Intent(applicationContext, MainActivity::class.java)
                startActivity(intent)
                finish()
            }else {
                val intent = Intent(applicationContext, NotificationResultActivity::class.java)
                intent.putExtra("companyUserid", id.toInt())
                startActivity(intent)
                finish()

            }
        }

    }
    override fun onNewIntent(intent : Intent) {
        super.onNewIntent(intent)
        setIntent(intent)
        val extras = intent.getExtras();

        if (extras?.getString("company_id") != null) {

                id = extras.getString("company_id")!!

        }
    }
     public override fun onDestroy() {

        if (mDelayHandler != null) {
            mDelayHandler!!.removeCallbacks(mRunnable)
        }

        super.onDestroy()
    }
}
