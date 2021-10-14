package com.example.shofna.presentation

import android.content.Context
import android.content.Intent
import android.os.Handler
import android.view.View
import androidx.fragment.app.Fragment
import com.example.shofna.R
import com.example.shofna.presentaion.homefragment.HomeFragment
import com.example.shofna.presentation.menufragment.MenuFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar

import com.yalantis.contextmenu.lib.ContextMenuDialogFragment
import com.yalantis.contextmenu.lib.MenuObject
import com.yalantis.contextmenu.lib.MenuParams
import kotlinx.android.synthetic.main.activity_main_content.*
import kotlinx.android.synthetic.main.bottom_nav_content.*
import kotlinx.android.synthetic.main.main_frame_content.*


class Menus {
    var chipNavigationBar: ChipNavigationBar? = null
    lateinit var contextMenuDialogFragment: ContextMenuDialogFragment

    var context: Context? = null

    //////// Horizontal Menu /////

    fun bottomMenu(context: Context) {

        chipNavigationBar = (context as MainActivity).bottom_nav_bar

        /// bottom bar

        chipNavigationBar!!.setItemSelected(R.id.bottom_nav_bar, true)
        context.supportFragmentManager.beginTransaction()
            .replace(R.id.main_frame, HomeFragment()).commit()

        /// default fragment
        context.bottom_nav_bar.setItemSelected(R.id.home)

        chipNavigationBar?.setOnItemSelectedListener(object :
            ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(i: Int) {
                when (i) {
                    R.id.home -> {

                        switchFragment(HomeFragment(), context)

                    }

                    R.id.settings -> {


                        switchFragment(MenuFragment(), context)



                    }
                }

            }
        })
    }

    fun switchFragment(fragment: Fragment, context: Context) {

        (context as MainActivity).supportFragmentManager.beginTransaction()
            .setCustomAnimations(R.anim.slide_right, 0, 0, 0)
            .replace(R.id.main_frame, fragment).addToBackStack(null).commit()



    }




}