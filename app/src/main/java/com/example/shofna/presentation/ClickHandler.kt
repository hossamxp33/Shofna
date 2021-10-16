package com.example.shofna.presentation

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Switch
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.shofna.R
import com.example.shofna.helper.PreferenceHelper
import com.example.shofna.helper.checkUserLogin
import com.example.shofna.presentaion.homefragment.HomeFragment
import com.example.shofna.presentation.registerActivity.RegisterActivity
import java.text.FieldPosition


class ClickHandler {


    fun Switch_to_Register_Activity(context: Context){

        val homeIntent = Intent(context, RegisterActivity()::class.java)
        (context as MainActivity).startActivity(homeIntent)


    }

    fun Logout(context: Context){

            if (checkUserLogin(context!!)) {
                PreferenceHelper.setToken("",context)
                PreferenceHelper.setUserGroupId(0)
                PreferenceHelper.setUserId(0)

                Toast.makeText(context, "تم تسجيل خروجك", Toast.LENGTH_SHORT).show()

                val homeIntent = Intent(context, RegisterActivity::class.java)
                (context as MainActivity) .overridePendingTransition(R.anim.slide_right, R.anim.slide_left);

                ( context as MainActivity).startActivity(homeIntent, ActivityOptions.makeSceneTransitionAnimation(context).toBundle())

            }else {
                val homeIntent = Intent(context, RegisterActivity::class.java)
                (context as MainActivity) .overridePendingTransition(R.anim.slide_right, R.anim.slide_left);

                ( context as MainActivity).startActivity(homeIntent, ActivityOptions.makeSceneTransitionAnimation(context).toBundle())

            }

    }

fun switchToNews(context: Context,position: Int){
    val bundle = Bundle()
    val frag = HomeFragment()
    frag.arguments = bundle
    bundle.putInt("position", position)
    switchFragment(frag,context)

}

    fun switchFragment(fragment: Fragment, context: Context) {

        (context as MainActivity).supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_right, 0, 0, 0)
            .replace(R.id.main_frame, fragment).addToBackStack(null).commit()



    }


}