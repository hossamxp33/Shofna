package com.example.shofna.presentation.webview

import android.os.Bundle
import android.webkit.WebViewClient
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.web_view.*
import android.webkit.WebView
import android.view.View
import com.example.shofna.R


class WebViewActivity: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)
        webView.webViewClient = WebViewClient()
        webView.settings.setSupportZoom(true)
        webView.settings.javaScriptEnabled = true


        val extras = intent.extras


      val url = extras?.getString("link")



        val webview = findViewById<View>(R.id.webView) as WebView

        webview.settings.javaScriptEnabled = true



      webview.loadUrl(url!!)

        webView.requestFocus();


        val webSettings = webView!!.getSettings()
        webSettings.setJavaScriptEnabled(true)
        // Force links and redirects to open in the WebView instead of in a browser
        webView!!.setWebViewClient(WebViewClient())
    }
}