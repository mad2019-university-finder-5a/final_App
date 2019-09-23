package com.example.ozedu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ContactUSActivity extends AppCompatActivity {
    DBHelper myDb;
    EditText editName,editEmail,editPhone,editMessage,editID;
    Button Cbutton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact_us);
        myDb = new DBHelper(this);
        editName=(EditText)findViewById(R.id.editText);
        editEmail=(EditText)findViewById(R.id.editText2);
        editPhone=(EditText)findViewById(R.id.editText3);
        editMessage=(EditText)findViewById(R.id.editText4);
        Cbutton=(Button)findViewById(R.id.button);
        AddData();
    }
    public void AddData (){
        Cbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isInserted = myDb.insertData(editName.getText().toString(),
                        editEmail.getText().toString(),
                        editPhone.getText().toString(),
                        editMessage.getText().toString());
                if(isInserted == true)
                    Toast.makeText(ContactUSActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ContactUSActivity.this,"error",Toast.LENGTH_LONG).show();
            }
        });
    }
}
