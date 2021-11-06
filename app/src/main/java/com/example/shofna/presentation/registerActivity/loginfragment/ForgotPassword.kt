package com.example.shofna.presentation.registerActivity.loginfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.web_view.*
import android.webkit.WebView
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.setContentView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import com.example.shofna.R
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.forgotpw_fragment.*
import kotlinx.android.synthetic.main.forgotpw_fragment.view.*


class ForgotPasswordActivity: AppCompatActivity() {
    private val viewModel: MainViewModel by lazy {
        ViewModelProviders.of(this).get(MainViewModel::class.java)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forgotpw_fragment)


       Send.setOnClickListener {
            viewModel.forgotPassword(email.text.toString())
        }

        viewModel.ForgotPasswordLD?.observe(this){
            if (it.state == "200"){
                Toast.makeText(this,it.data, Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this,"خطأ في الايميل حاول مرة اخري", Toast.LENGTH_SHORT).show()
            }
        }



    }
}