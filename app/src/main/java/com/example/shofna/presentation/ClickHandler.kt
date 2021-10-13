package com.example.shofna.presentation

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Switch
import androidx.fragment.app.Fragment
import com.example.shofna.R
import com.example.shofna.presentation.registerActivity.RegisterActivity


class ClickHandler {


    fun Switch_to_Register_Activity(context: Context){

        val homeIntent = Intent(context, RegisterActivity()::class.java)
        (context as MainActivity).startActivity(homeIntent)


    }



    fun switchFragment(fragment: Fragment, context: Context) {

        (context as MainActivity).supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_right, 0, 0, 0)
            .replace(R.id.main_frame, fragment).addToBackStack(null).commit()



    }


}