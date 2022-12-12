package com.noob.rebirthsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class AboutActivity extends AppCompatActivity {
    //实例控件
    Button githubbtn;
    WebView githubview;
    TextView titleview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //控件初始化
        githubbtn=findViewById(R.id.gogithub);
        githubview=findViewById(R.id.webview);
        titleview=findViewById(R.id.titleview);
        //github按钮
        githubbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                githubview.loadUrl("https://github.com/Violetmail/RebirthSimulator");
                githubbtn.setVisibility(View.GONE);
                titleview.setVisibility(View.GONE);
            }
        });

    }
}