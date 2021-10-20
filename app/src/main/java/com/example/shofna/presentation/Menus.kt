package com.example.shofna.presentation

import android.content.Context
import androidx.fragment.app.Fragment
import com.example.shofna.R
import com.example.shofna.presentaion.homefragment.DepartmentsFragment
import com.example.shofna.presentaion.homefragment.NotificationsFragment
import com.example.shofna.presentation.homefragment.HomeFragment
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel
import com.example.shofna.presentation.menufragment.MenuFragment
import com.ismaeldivita.chipnavigation.ChipNavigationBar

import com.yalantis.contextmenu.lib.ContextMenuDialogFragment
import kotlinx.android.synthetic.main.bottom_nav_content.*


class Menus {
    var chipNavigationBar: ChipNavigationBar? = null
    lateinit var contextMenuDialogFragment: ContextMenuDialogFragment
    private var activeFragment: Fragment = HomeFragment()
    lateinit var viewModel: MainViewModel
    var context: Context? = null

    //////// Horizontal Menu /////

    fun bottomMenu(context: Context) {

        chipNavigationBar = (context as MainActivity).bottom_nav_bar

        /// bottom bar

        chipNavigationBar!!.setItemSelected(R.id.bottom_nav_bar, true)

        switchFragment(activeFragment, context)

        /// default fragment
        context.bottom_nav_bar.setItemSelected(R.id.home)

        chipNavigationBar?.setOnItemSelectedListener(object :
            ChipNavigationBar.OnItemSelectedListener {
            override fun onItemSelected(i: Int) {
                when (i) {

                    R.id.home -> {
                        switchFragment(HomeFragment(), context)

                    }
                    R.id.departments -> {

                        switchFragment(DepartmentsFragment(), context)

                    }
                    R.id.notification -> {

                        switchFragment(NotificationsFragment(), context)

                    }
                    R.id.settings -> {


                        switchFragment(MenuFragment(), context)

                    } else -> false
                }
            }
        })
    }

    fun switchFragment(fragment: Fragment, context: Context) {

        (context as MainActivity).supportFragmentManager.beginTransaction().setReorderingAllowed(true)
            .setCustomAnimations(R.anim.slide_right, 0, 0, 0)
            .replace(R.id.main_frame, fragment).addToBackStack(null).commit()

    }




}