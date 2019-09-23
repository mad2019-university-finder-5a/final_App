package com.example.ozedu;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
public class AllAboutAustralia extends AppCompatActivity {
    Button btn_profile,btn_universities,btn_careers,btn_livingInAustralia,btn_feedback,btn_contactUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_about_australia);
        btn_profile = (Button)findViewById(R.id.buttonProfile);
        btn_universities = (Button)findViewById(R.id.buttonUniversity);
        btn_careers = (Button)findViewById(R.id.buttonScholarships);
        btn_livingInAustralia = (Button)findViewById(R.id.buttonLiving);
        btn_feedback = (Button)findViewById(R.id.buttonweather);
        btn_contactUs = (Button)findViewById(R.id.buttoncontact);

        btn_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AllAboutAustralia.this,"Profile clicked",Toast.LENGTH_LONG).show();

                Intent i = new Intent(AllAboutAustralia.this,ProfileActivity.class);
                startActivity(i);
            }
        });

        btn_universities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AllAboutAustralia.this,"Universities clicked",Toast.LENGTH_LONG).show();

                Intent i = new Intent(AllAboutAustralia.this,ASearchActivity.class);
                startActivity(i);
            }
        });

        btn_careers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AllAboutAustralia.this,"Careers clicked",Toast.LENGTH_LONG).show();

                Intent i = new Intent(AllAboutAustralia.this,JobFinderActivity.class);
                startActivity(i);
            }
        });

        btn_livingInAustralia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AllAboutAustralia.this,"Living in Australia clicked",Toast.LENGTH_LONG).show();

                Intent i = new Intent(AllAboutAustralia.this, LivingInAustralia.class);
                startActivity(i);
            }
        });

        btn_feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AllAboutAustralia.this,"Feedback clicked",Toast.LENGTH_LONG).show();

                Intent i = new Intent(AllAboutAustralia.this,FeedbackActivity.class);
                startActivity(i);
            }
        });

        btn_contactUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(AllAboutAustralia.this,"Contact us clicked",Toast.LENGTH_LONG).show();

                Intent i = new Intent(AllAboutAustralia.this,ContactUSActivity.class);
                startActivity(i);
            }
        });
    }
}
