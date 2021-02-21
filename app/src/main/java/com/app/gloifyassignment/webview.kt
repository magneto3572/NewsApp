package com.app.gloifyassignment

import android.os.Build
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

class webview : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_webview)
        val webView = findViewById<WebView>(R.id.webview)
        webView.settings.javaScriptEnabled
        val url = intent.getStringExtra("url")
        webView.webViewClient = WebViewClient()
        webView.loadUrl(url.toString())
    }
}