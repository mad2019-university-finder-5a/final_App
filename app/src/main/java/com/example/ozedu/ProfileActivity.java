package com.example.ozedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ProfileActivity extends AppCompatActivity {
    Button person;
    Button academic;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        person =(Button)findViewById(R.id.button);
        academic=(Button)findViewById(R.id.button2);

        person.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactpage= new Intent(ProfileActivity.this,personalActivity.class);
                startActivity(contactpage);
            }
        });

        academic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contactpage= new Intent(ProfileActivity.this,academicActivity.class);
                startActivity(contactpage);
            }
        });


    }


            }
