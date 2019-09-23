package com.example.ozedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AdminSettingActivity extends AppCompatActivity {
    Button HomeButton;
    Button AdminConButton;
    DatabaseHelper db;
    DBHelper mydb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_setting);

        db = new DatabaseHelper(this);
        mydb=new DBHelper(this);
        HomeButton=(Button)findViewById(R.id.AdminHome);
        AdminConButton=(Button)findViewById(R.id.AdminContact);
        HomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent HomeView =new Intent(AdminSettingActivity.this,AllAboutAustralia.class);
                startActivity(HomeView);
            }
        });

        AdminConButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ContactView =new Intent(AdminSettingActivity.this,SettingContactActivity.class);
                startActivity(ContactView);
            }
        });
    }
}
