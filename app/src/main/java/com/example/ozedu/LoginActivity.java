package com.example.ozedu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText mTextUsername;
    EditText mTextPassword;
    Button mButtonLogin;
    TextView mTextViewRegister;
    CheckBox showpassword;
    LDBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new LDBHelper(this);
        mTextUsername =(EditText)findViewById(R.id.edittext_user);
        mTextPassword=(EditText)findViewById(R.id.edittext_pass);
        mButtonLogin=(Button)findViewById(R.id.button_login);
        mTextViewRegister=(TextView) findViewById(R.id.textview_reg);
        showpassword =(CheckBox)findViewById(R.id.showpassword);
        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    mTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else
                {
                    mTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });

        mTextViewRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent =new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(registerIntent);
            }
        });

        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                Boolean res = db.checkUser(user, pwd);
                if ((user.isEmpty()) || (pwd.isEmpty())) {
                    Toast.makeText(LoginActivity.this, "Please enter details", Toast.LENGTH_SHORT).show();


                } else {

                    if (res == true) {

                        if (user.equals("Mary Jayamaha") && pwd.equals("mary123")) {
                            Intent SettingPage = new Intent(LoginActivity.this, AdminSettingActivity.class);
                            startActivity(SettingPage);
                        } else {
                            Intent HomePage = new Intent(LoginActivity.this, AllAboutAustralia.class);
                            startActivity(HomePage);
                            Toast.makeText(LoginActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Error", Toast.LENGTH_SHORT).show();
                    }


                }
            }
        });





    }
}
