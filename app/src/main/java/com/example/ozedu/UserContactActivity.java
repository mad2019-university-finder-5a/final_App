package com.example.ozedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;

public class UserContactActivity extends AppCompatActivity {
    Button UserContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_contact);


        String myurll="file:///android_asset/contact.html";
        WebView view=(WebView)this.findViewById(R.id.CwebViwe);
        view.getSettings().setJavaScriptEnabled(true);
        view.loadUrl(myurll);


    }
}
