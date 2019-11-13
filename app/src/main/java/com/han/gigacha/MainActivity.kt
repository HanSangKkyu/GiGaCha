package com.han.gigacha

import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.widget.Toast
import android.webkit.WebViewClient



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val myWebView = findViewById<View>(R.id.webview) as WebView

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            WebView.setWebContentsDebuggingEnabled(true)
        }

        myWebView.setWebChromeClient(object : WebChromeClient() {
            override fun onJsAlert(view: WebView, url: String, message: String, result: JsResult): Boolean {
                Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
                result.cancel()
                return true
            }
        })

        val settings = myWebView.settings
        settings.javaScriptEnabled = true
        settings.domStorageEnabled = true

        myWebView.setWebViewClient(WebViewClient()) // a 태그 링크 연결시 웹뷰내에서 페이지 이동

        myWebView.loadUrl("http://58.141.61.11:8888/")
    }


    override fun onBackPressed() {
        // 물리 버튼으로 뒤로가기
        val myWebView = findViewById<View>(R.id.webview) as WebView
        if (myWebView.canGoBack()) {
            myWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }
}
