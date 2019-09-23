package com.example.ozedu;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingContactActivity extends AppCompatActivity {
    DBHelper myDb;
    EditText editName,editEmail,editPhone,editMessage,editID;
    Button Cbutton;
    Button VeButton;
    Button UpButton;
    Button DButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_contact);
        myDb = new DBHelper(this);
        editName=(EditText)findViewById(R.id.editText);
        editEmail=(EditText)findViewById(R.id.editText2);
        editPhone=(EditText)findViewById(R.id.editText3);
        editMessage=(EditText)findViewById(R.id.editText4);
        editID=(EditText)findViewById(R.id.editText5);
        Cbutton=(Button)findViewById(R.id.button);
        VeButton=(Button)findViewById(R.id.button2);
        UpButton=(Button)findViewById(R.id.button3);
        DButton=(Button)findViewById(R.id.button4);
        AddData();
        viewAll();
        UpdateData();
        DeleteData();

    }

    public void DeleteData(){
        DButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer deletedRows = myDb.deleteData(editID.getText().toString());
                if(deletedRows > 0)
                    Toast.makeText(SettingContactActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(SettingContactActivity.this,"not Deleted ",Toast.LENGTH_LONG).show();

            }
        });
    }

    public void UpdateData(){
        UpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean isUpdate=myDb.updateData(editID.getText().toString(),
                        editName.getText().toString(),
                        editEmail.getText().toString(),
                        editPhone.getText().toString(),
                        editMessage.getText().toString());
                if(isUpdate==true)
                    Toast.makeText(SettingContactActivity.this,"Data Updated",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(SettingContactActivity.this,"not updated ",Toast.LENGTH_LONG).show();

            }
        });
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
                    Toast.makeText(SettingContactActivity.this,"Data inserted",Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(SettingContactActivity.this,"error",Toast.LENGTH_LONG).show();
            }
        });
    }

    public void viewAll(){
        VeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor res = myDb.getAllData();
                if(res.getCount()==0){
                    //show message
                    showMessage("Error","Nothing found");
                    return;
                }
                StringBuffer buffer= new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("Id :"+res.getString(0)+"\n");
                    buffer.append("Name: "+res.getString(1)+"\n");
                    buffer.append("Email: "+res.getString(2)+"\n");
                    buffer.append("Phonen: "+res.getString(3)+"\n");
                    buffer.append("Message: "+res.getString(4)+"\n");

                }
                //show all data
                showMessage("Data",buffer.toString());


            }
        });

    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }



}
