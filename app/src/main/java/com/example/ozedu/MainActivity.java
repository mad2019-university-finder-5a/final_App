package com.example.ozedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button fisrtpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
                finish();
            }
        },4000);

        fisrtpage=(Button)findViewById(R.id.UFirst);

        fisrtpage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent first= new Intent(MainActivity.this,LoginActivity.class);
                startActivity(first);
            }
        });

    }
}
