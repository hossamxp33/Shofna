package com.example.shofna.presentation.registerActivity.webviewlogin

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.shofna.R
import com.example.shofna.helper.PreferenceHelper
import com.example.shofna.presentation.MainActivity
import com.example.shofna.presentation.homefragment.viewmodel.MainViewModel
import com.example.shofna.presentation.registerActivity.RegisterActivity
import com.example.shofna.presentation.registerActivity.loginfragment.ForgotPasswordActivity
import kotlinx.android.synthetic.main.login_web_view.view.*
import kotlinx.android.synthetic.main.web_view.*

class WebViewLogin: Fragment() {


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val view = inflater.inflate(R.layout.login_web_view, container, false)
      view.webView.webViewClient = WebViewClient()
        view.webView.settings.setSupportZoom(true)
        view.webView.settings.javaScriptEnabled = true

        val webview =  view.webView as WebView



        webview.loadUrl("https://goldenlinktele.com/login/")

        webview.requestFocus();


        val webSettings =   view.webView.getSettings()
        webSettings.setJavaScriptEnabled(true)
        // Force links and redirects to open in the WebView instead of in a browser

        webview.webViewClient = WebViewClient()

// Use
        val handler = Handler()
        handler.postDelayed({
            view.progress.isVisible = false

        }, 1000)
        return view
    }

}