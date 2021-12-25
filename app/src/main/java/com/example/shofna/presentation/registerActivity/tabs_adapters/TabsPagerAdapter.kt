package com.example.shofna.presentation.registerActivity.tabs_adapters

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.shofna.presentation.registerActivity.loginfragment.LoginFragment
import com.example.shofna.presentation.registerActivity.registerfragment.RegisterFragment
import com.example.shofna.presentation.registerActivity.webviewlogin.WebViewLogin
import com.example.shofna.presentation.registerActivity.webviewlogin.WebViewRegister


class TabsPagerAdapter(fm: FragmentManager, lifecycle: Lifecycle, private var numberOfTabs: Int) : FragmentStateAdapter(fm, lifecycle) {

    override fun createFragment(position: Int): Fragment {
        when (position) {
            0 -> {
                // # Music Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "Music Fragment")
                val login =
                    WebViewLogin()
                login.arguments = bundle
                return login
            }
            1 -> {
                // # Movies Fragment
                val bundle = Bundle()
                bundle.putString("fragmentName", "Movies Fragment")
                val registerFragment =
                    WebViewRegister()
                registerFragment.arguments = bundle
                return registerFragment
            }

            else -> return LoginFragment()
        }
    }

    override fun getItemCount(): Int {
        return numberOfTabs
    }
}