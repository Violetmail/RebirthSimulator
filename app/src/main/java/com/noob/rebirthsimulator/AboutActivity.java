package com.noob.rebirthsimulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class AboutActivity extends AppCompatActivity {
    //实例控件
    Button githubbtn;
    WebView githubview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //控件初始化
        githubbtn=findViewById(R.id.gogithub);
        githubview=findViewById(R.id.webview);
        //github按钮
        githubbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                githubview.loadUrl("https://github.com/Violetmail/RebirthSimulator");
            }
        });

    }
}