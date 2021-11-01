package com.example.shofna.presentation.registerActivity

import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.graphics.BlendModeColorFilterCompat
import androidx.core.graphics.BlendModeCompat
import com.example.shofna.R
import com.example.shofna.helper.PreferenceHelper
import com.example.shofna.helper.checkUserLogin
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.registerActivity.tabs_adapters.TabsPagerAdapter
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.register_activity.*

class RegisterActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      PreferenceHelper(this)
        setContentView(R.layout.register_activity)

//        if (checkUserLogin(this)) {
//
//            val homeIntent = Intent(this, MainActivity::class.java)
//            overridePendingTransition(R.anim.slide_right, R.anim.slide_left);
//            startActivity(homeIntent)
//
//
//        }

        tab_layout.setBackgroundColor(ContextCompat.getColor(this, R.color.light_gray))
        // Number Of Tabs
        val numberOfTabs = 2
        // Show all Tabs in screen
        tab_layout.tabMode = TabLayout.MODE_FIXED
        // Scroll to see all Tabs
        //tab_layout.tabMode = TabLayout.MODE_SCROLLABLE
        // Set divider
        tab_layout.isInlineLabel = true
        val root: View = tab_layout.getChildAt(0)
        if (root is LinearLayout) {
            (root as LinearLayout).showDividers = LinearLayout.SHOW_DIVIDER_MIDDLE
            val drawable = GradientDrawable()
            drawable.setColor(getColor(R.color.red))
            drawable.setSize(2, 1)
            (root as LinearLayout).dividerPadding = 10
            (root as LinearLayout).dividerDrawable = drawable
        }
        // Set the ViewPager Adapter
        val adapter =
            TabsPagerAdapter(supportFragmentManager, lifecycle, numberOfTabs)
        tabs_viewpager.adapter = adapter
        // Enable Swipe
        tabs_viewpager.isUserInputEnabled = true
        // Link the TabLayout and the ViewPager2 together and Set Text & Icons
        TabLayoutMediator(tab_layout, tabs_viewpager) { tab, position ->
            when (position) {
                0 -> {
                    tab.text = this.getText(R.string.login)
                    //   tab.setIcon(R.drawable.ic_music)
                }
                1 -> {
                    tab.text = this.getText(R.string.register)
                    //    tab.setIcon(R.drawable.ic_movie)

                }

            }
            // Change color of the icons
            tab.icon?.colorFilter =
                BlendModeColorFilterCompat.createBlendModeColorFilterCompat(
                    Color.WHITE,
                    BlendModeCompat.SRC_ATOP
                )
        }.attach()


    }
}