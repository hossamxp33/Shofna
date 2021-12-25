package com.example.shofna.presentation.registerActivity.registerfragment

import android.content.Intent
import android.opengl.Visibility
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.shofna.R
import com.example.shofna.helper.PreferenceHelper
import com.example.shofna.model.User
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel
import com.yalantis.contextmenu.lib.extensions.ALPHA_VISIBLE
import kotlinx.android.synthetic.main.login_fragment.view.*
import kotlinx.android.synthetic.main.login_fragment.view.password
import kotlinx.android.synthetic.main.login_fragment.view.username
import kotlinx.android.synthetic.main.menu_fragment.view.*
import kotlinx.android.synthetic.main.register_fragment.view.*
import org.jetbrains.anko.support.v4.find


class RegisterFragment : Fragment() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    val progressBar: ProgressBar? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.register_fragment, container, false)
        var emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"

        viewModel.processVisibility.observe(requireActivity()) {
            view.progress?.isVisible = it
        }
        view.register.setOnClickListener {
            val password = view.password?.text.toString()
            if (view.username.text.toString() == "" && view.mobile.text.toString() == "" && view.email.text.toString() == "") {
                Toast.makeText(requireContext(), "الرجاء أكمل البيانات", Toast.LENGTH_SHORT).show()

            } else if (!view.email.text.toString().trim { it <= ' ' }
                    .matches(emailPattern.toRegex())) {
                Toast.makeText(requireContext(), "valid email address", Toast.LENGTH_SHORT).show()

            } else {
                viewModel.GetRegisterData(
                    view.username.text.toString(),
                    view.mobile.text.toString(),
                    view.email.text.toString(),
                    password
                )

            }


        }

        viewModel.RegisterDataLD?.observe(requireActivity(), Observer {
            viewModel.processVisibility.value = true

            if (it.success == false) {
                Toast.makeText(context, it.data?.message, Toast.LENGTH_SHORT).show()
                viewModel.processVisibility.value = false

            } else {
                viewModel.processVisibility.value = true

                PreferenceHelper.setUsername(it.data?.username)

                PreferenceHelper.setToken(it.data?.token, context)

//                PreferenceHelper.setUserId(it.data?.userid!!)

                Toast.makeText(context, "تم انشاء حساب", Toast.LENGTH_SHORT).show()
                viewModel.processVisibility.value = false

//
//                val intent = Intent(requireActivity(), MainActivity::class.java)
//
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//
//                startActivity(intent)
            }

        })

        viewModel.errorLivedat.observe(requireActivity(), Observer {
            Toast.makeText(context, it, Toast.LENGTH_SHORT).show()

        })
        return view
    }

}