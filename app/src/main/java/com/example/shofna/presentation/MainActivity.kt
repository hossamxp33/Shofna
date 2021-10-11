package com.example.shofna.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.shofna.R
import com.example.shofna.helper.PreferenceHelper
import com.example.shofna.helper.ResourceUtil
import com.example.shofna.presentaion.homefragment.HomeFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        PreferenceHelper(this)
        ResourceUtil().changeLang("ar",this)
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.ttb, 0, 0, 0)
            .replace(R.id.main_frame, HomeFragment()).addToBackStack(null).commit()

    }
}