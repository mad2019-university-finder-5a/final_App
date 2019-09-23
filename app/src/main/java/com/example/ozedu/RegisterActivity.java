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

public class RegisterActivity extends AppCompatActivity {

    LDBHelper db;
    EditText mTextUsername;
    EditText mTextPassword;
    EditText mTextCnfPassword;
    Button mButtonRegister;
    TextView mTextViewLogin;
    CheckBox showpassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new LDBHelper(this);
        mTextUsername =(EditText)findViewById(R.id.edittext_user);
        mTextPassword=(EditText)findViewById(R.id.edittext_pass);
        mTextCnfPassword=(EditText)findViewById(R.id.edittext_Conpass);
        mButtonRegister=(Button)findViewById(R.id.button_register);
        mTextViewLogin=(TextView) findViewById(R.id.textview_login);
        showpassword =(CheckBox)findViewById(R.id.showpassword);
        showpassword.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b)
                {
                    mTextCnfPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());

                }else
                {
                    mTextCnfPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }

            }
        });

        mTextViewLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent LoginIntent =new Intent(RegisterActivity.this,MainActivity.class);
                startActivity(LoginIntent);

            }
        });
        mButtonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = mTextUsername.getText().toString().trim();
                String pwd = mTextPassword.getText().toString().trim();
                String cnfpwd = mTextCnfPassword.getText().toString().trim();
                if ((user.isEmpty()) || (pwd.isEmpty())) {
                    Toast.makeText(RegisterActivity.this, "Please enter details", Toast.LENGTH_SHORT).show();


                } else {
                    if(user.isEmpty() || user.length()<8 ){
                        mTextUsername.setError("User name cannot be less than 8 characters !");

                    }else {
                        if (pwd.isEmpty() || pwd.length() < 4) {
                            mTextPassword.setError("Password cannot be less than 4 characters!");

                        } else {

                            if (pwd.equals(cnfpwd)) {
                                long val = db.addUser(user, pwd);
                                if (val > 0) {
                                    Toast.makeText(RegisterActivity.this, " You have registered ", Toast.LENGTH_SHORT).show();
                                    Intent moveToLogin = new Intent(RegisterActivity.this, MainActivity.class);
                                    startActivity(moveToLogin);
                                } else {
                                    Toast.makeText(RegisterActivity.this, "Registeration error", Toast.LENGTH_SHORT).show();
                                }

                            } else {
                                Toast.makeText(RegisterActivity.this, "password is not macth", Toast.LENGTH_SHORT).show();
                            }
                            mTextPassword.setError(null);
                        }
                        mTextUsername.setError(null);
                    }
                }


            }
        });
    }
}
