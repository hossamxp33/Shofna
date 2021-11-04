package com.example.shofna.presentation.registerActivity.loginfragment

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.shofna.R
import com.example.shofna.helper.PreferenceHelper
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel
import com.example.shofna.presentation.registerActivity.RegisterActivity
import com.example.shofna.presentation.webview.WebViewActivity
import kotlinx.android.synthetic.main.login_fragment.view.*
import kotlinx.android.synthetic.main.menu_fragment.view.login


class LoginFragment: Fragment() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.login_fragment, container, false)

        view.login.setOnClickListener {
            viewModel.GetLoginData(view.username.text.toString(), view.password.text.toString())
        }

        view.forgetPW.setOnClickListener {
            val intent = Intent(this.activity, ForgotPasswordActivity::class.java)

            (requireActivity()).startActivity(intent)
        }

        viewModel.LoginDataLD?.observe(requireActivity() , Observer {

            if (it.token == null){

                Toast.makeText(context , "خطأ في كلمة المرور او كلمة السر", Toast.LENGTH_SHORT).show()

            }else {

                PreferenceHelper.setUsername(it.username)
                PreferenceHelper.setToken(it.token,context)
                PreferenceHelper.setUserId(it.userid!!)


                val intent = Intent(requireActivity(), MainActivity::class.java)

                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

                startActivity(intent)
            }

        })



        viewModel.errorLivedat.observe(requireActivity(), Observer {
            Toast.makeText(context , "خطأ في كلمة المرور او كلمة السر", Toast.LENGTH_SHORT).show()
        })
        return view
    }

}