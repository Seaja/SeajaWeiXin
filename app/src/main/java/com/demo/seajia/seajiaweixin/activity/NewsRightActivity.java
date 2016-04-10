package com.demo.seajia.seajiaweixin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.demo.seajia.seajiaweixin.R;

/**
 * Created by S.F.S on 2016/4/8.
 */
public class NewsRightActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.fragment_right);

        Intent intent=getIntent();
        String contentURL=intent.getStringExtra("URL");
        WebView webView= (WebView) findViewById(R.id.webview);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {

                view.loadUrl(url);
                return true;
            }
        });

        webView.loadUrl(contentURL);

    }
}
