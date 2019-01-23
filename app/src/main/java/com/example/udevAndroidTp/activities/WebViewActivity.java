package com.example.udevAndroidTp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;

import com.example.udevAndroidTp.R;

public class WebViewActivity extends AppCompatActivity {

    WebView googleWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        //initiateLayouts()
        googleWebView = findViewById(R.id.googleWebView);

        //behaviorViews()
        googleWebView.getSettings().setJavaScriptEnabled(true);

        googleWebView.loadUrl(getIntent().getSerializableExtra("url").toString());

    }
}
