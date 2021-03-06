package com.yxliu.demowebview;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yxliu.demowebview.injector.WebViewInjector;

public class WebViewActivity extends AppCompatActivity {

    @Override
    @SuppressLint("SetJavaScriptEnabled")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        WebView webView = findViewById(R.id.web_view);
        WebViewInjector injector = new WebViewInjector();
        injector.injectToWebView(webView);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());

        Intent intent = this.getIntent();
        String url = intent.getStringExtra("URL");
        
        webView.loadUrl(url);
    }
}
