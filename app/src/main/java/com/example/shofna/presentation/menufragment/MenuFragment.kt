package com.example.shofna.presentation.menufragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.graphics.convertTo
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.shofna.R
import com.example.shofna.databinding.MenuFragmentBinding
import com.example.shofna.helper.PreferenceHelper
import com.example.shofna.presentation.ClickHandler
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.registerActivity.RegisterActivity

class MenuFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view: MenuFragmentBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.menu_fragment, container, false
        )

        view.click = ClickHandler()
        view.context = context as MainActivity


        if (!PreferenceHelper.getUsername().isNullOrEmpty())
            view.myname.text = PreferenceHelper.getUsername()

        if (PreferenceHelper.getUserId() > 0)
            view.loginLayout.setVisibility(View.GONE) else {
            view.logoutLayout.setVisibility(
                View.GONE
            ); view.EditProfileLayout.setVisibility(
                View.GONE
            )
            view.EditPwLayout.setVisibility(
                View.GONE
            )
        }

        return view.root

    }
}