package com.wangban.yzbbanban.test_webview;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity {
    private WebView webView;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = (WebView) findViewById(R.id.webView);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl("http://m.xxxiao.com");
                return true;
            }
        });
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new Object() {
            public void clickOnAndroid() {
                handler.post(new Runnable() {
                    public void run() {
                        webView.loadUrl("javascript:wave()");
                    }
                });
            }
        }, "demo");

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:
                webView.goBack();
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
