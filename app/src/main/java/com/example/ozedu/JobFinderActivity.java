package com.example.ozedu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

public class JobFinderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_finder);
        String myurl="file:///android_asset/index.html";
        WebView view=(WebView)this.findViewById(R.id.webViwe);
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(myurl);
    }
}
