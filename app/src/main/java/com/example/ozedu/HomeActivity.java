package com.example.ozedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {
    Button contactButton;
    Button ConsettingButton;
    Button feedback;
    DatabaseHelper db;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = new DatabaseHelper(this);
        mydb=new DBHelper(this);
        ConsettingButton= (Button)findViewById(R.id.ConSettingB);
        contactButton = (Button)findViewById(R.id.HContact);
        feedback=(Button)findViewById(R.id.feed);
        contactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactpage= new Intent(HomeActivity.this,UserContactActivity.class);
                startActivity(contactpage);
            }
        });

        ConsettingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(HomeActivity.this,"profile activity clicked",Toast.LENGTH_LONG).show();
                Intent SettingContactpage = new Intent(HomeActivity.this,ProfileActivity.class);
                startActivity(SettingContactpage);
            }
        });

        feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent SettingContactpage = new Intent(HomeActivity.this,ASearchActivity.class);
                startActivity(SettingContactpage);
            }
        });




    }
}
