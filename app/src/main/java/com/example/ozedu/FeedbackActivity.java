package com.example.ozedu;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AlertDialog;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class FeedbackActivity extends AppCompatActivity {
   FDBHelper myDb;
    EditText Sname, ContactNo, email, feedback, FID;
    Button Add;
    Button View;
    Button Update;
    Button Delete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        myDb = new FDBHelper(this);

        Sname = (EditText) findViewById(R.id.Sname);
        ContactNo = (EditText) findViewById(R.id.ContactNo);
        email = (EditText) findViewById(R.id.email);
        feedback = (EditText) findViewById(R.id.feedback);
        FID = (EditText) findViewById(R.id.FID);
        Add = (Button) findViewById(R.id.Add);
        View = (Button) findViewById(R.id.View);
        Update = (Button) findViewById(R.id.Update);
        Delete = (Button) findViewById(R.id.Delete);
        AddFeedBak();
        ViewFeedBak();
        UpdateFeedback();
        DeleteFeedback();

    }

    public void DeleteFeedback(){
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(android.view.View view) {
                Integer deletedRows = myDb.deleteData(FID.getText().toString());
                if (deletedRows > 0)
                    Toast.makeText(FeedbackActivity.this, "Data is Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(FeedbackActivity.this, "Data is Not Deleted", Toast.LENGTH_LONG).show();
            }
        });
    }
    public void UpdateFeedback(){
        Update.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        boolean isUpdate = myDb.updateData(FID.getText().toString(),Sname.getText().toString(),
                                ContactNo.getText().toString(),
                                email.getText().toString(),
                                feedback.getText().toString());
                        if (isUpdate == true)
                            Toast.makeText(FeedbackActivity.this, "Data is Updated", Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(FeedbackActivity.this, "Data is Not Updated", Toast.LENGTH_LONG).show();
                    }
                }

        );
    }

    public void AddFeedBak() {
        try {

            Add.setOnClickListener(
                    new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            boolean isInserted = myDb.insertData(Sname.getText().toString(),
                                    ContactNo.getText().toString(),
                                    email.getText().toString(),
                                    feedback.getText().toString());
                            if (isInserted == true)
                                Toast.makeText(FeedbackActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                            else
                                Toast.makeText(FeedbackActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
            );
        }catch (Exception e){

        }


    }
    public void ViewFeedBak()   {
        View.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(android.view.View view) {
                        Cursor res = myDb.getAllData();
                        if(res.getCount() == 0){
                            // show massage
                            showMassage("Error","Nothing found");

                            return ;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()){
                            buffer.append("FID:"+ res.getString(0)+"\n");
                            buffer.append("Sname:"+ res.getString(1)+"\n");
                            buffer.append("ContactNo:"+ res.getString(2)+"\n");
                            buffer.append("email:"+ res.getString(3)+"\n");
                            buffer.append("feedback:"+ res.getString(4)+"\n\n");

                        }

                        //show all data
                        showMassage("Data",buffer.toString());

                    }
                }
        );
    }

    public void showMassage(String title,String Massage){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Massage);
        builder.show();
    }



}
