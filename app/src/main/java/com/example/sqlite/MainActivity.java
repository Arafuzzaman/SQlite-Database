package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    DatabaseHelper mydb;
    Button add;
    EditText Skill,Dep,Varsity_Name,name,Id;
    Button btnviewAll,btnviewUpdate,btnDelete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new DatabaseHelper(this);
        add=findViewById(R.id.ad1);
        Dep=findViewById(R.id.ed2);
        Skill=findViewById(R.id.ed3);
        Varsity_Name=findViewById(R.id.ed4);
        name=findViewById(R.id.ed5);
        btnviewAll=findViewById(R.id.vi1);
        btnviewUpdate=findViewById(R.id.up1);
        Id=findViewById(R.id.ed1);
        btnDelete=findViewById(R.id.de1);

        AddData();
        viewAll();
        UpdateData();
        DeleteData();
    }
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        Integer deletedRows =
                                mydb.deleteData(Id.getText().toString());
                        if (deletedRows >= -1 ) {
                            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Data not Deleted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
    public void UpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        boolean isUpdate =
                                mydb.updateData(Id.getText().toString(), Skill.getText().toString(), Dep.getText().toString(),Varsity_Name.getText().toString(),name.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void AddData() {
        add.setOnClickListener(
                new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        boolean isInserted =
                                mydb.insertData(Id.getText(), Skill.getText().toString(), Dep.getText().toString(), Varsity_Name.getText().toString(), name.getText().toString());
                        if(isInserted == true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }

                }
        );
    }
    public void viewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override

                    public void onClick(View v) {
                        Cursor res = mydb.getAllData();
                        if(res.getCount() == 0) {
// show message
                            showMessage("Error","Nothing found");
                            return;
                        }
                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+res.getString(0)+"\n");
                            buffer.append("name :"+res.getString(1)+"\n");
                            buffer.append("Varsity Name :"+res.getString(2)+"\n");
                            buffer.append("Skill :"+res.getString(3)+"\n");
                            buffer.append("Dep :"+res.getString(4)+"\n\n");


                        }
// Show all data

                        showMessage("Data",buffer.toString());
                    }
                });
    }
    public void showMessage(String experience,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        //builder.setexperience(experience);
        builder.setMessage(Message);
        builder.show();
    }
}