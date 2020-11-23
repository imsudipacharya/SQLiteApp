package com.sudipacharya.sqliteapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editId, editName, editSurName, editMarks;
    Button btnAddData,btnAllData,btnUpdateData,btnDeleteData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        editId = findViewById(R.id.editTextTextPersonName4);
        editName = findViewById(R.id.editTextTextPersonName);
        editSurName = findViewById(R.id.editTextTextPersonName2);
        editMarks = findViewById(R.id.editTextTextPersonName3);
        btnAddData = findViewById(R.id.addDataButton);
        btnAllData = findViewById(R.id.allDataButton);
        btnUpdateData = findViewById(R.id.updateButton);
        btnDeleteData = findViewById(R.id.deleteButton);
        AddData();
        ViewAllData();
        UpdateData();
        DeleteData();

    }

    public void AddData(){
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            boolean isInserted = myDb.insertData(editName.getText().toString()
                ,editSurName.getText().toString()
                ,editMarks.getText().toString());

            if (isInserted){
                Toast.makeText(MainActivity.this, "Data Inserted" , Toast.LENGTH_LONG).show();
            }else {
                Toast.makeText(MainActivity.this, "Data Not Inserted" , Toast.LENGTH_LONG).show();

            }

            }
        });
    }

    public void ViewAllData(){
        btnAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if (res.getCount() == 0){
                    //Show Empty Message
                    showMessage("Error", "No Data is Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID :"+ res.getString(0)+"\n");
                    buffer.append("NAME :"+ res.getString(1)+"\n");
                    buffer.append("SURNAME :"+ res.getString(2)+"\n");
                    buffer.append("MARKS :"+ res.getString(3)+"\n\n");
                }
                //show all data
                showMessage("Datas",buffer.toString());
            }
        });
    }
    public void showMessage(String title , String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

    public void UpdateData(){
        btnUpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isUpdate = myDb.updateData(
                        editId.getText().toString()
                        ,editName.getText().toString()
                        ,editSurName.getText().toString()
                        ,editMarks.getText().toString()
                );
                if (isUpdate){
                    //show message
                    Toast.makeText(MainActivity.this, "Data Updated" , Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(MainActivity.this, "Data Not Updated" , Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    public void DeleteData(){
        btnDeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deletedRows = myDb.deleteData(editId.getText().toString());
                if (deletedRows > 0){
                    Toast.makeText(MainActivity.this, "Data Deleted" , Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(MainActivity.this, "Data Not Deleted" , Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    /*
     public void ViewAllData(){
        btnAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.GetId(editId.getText().toString());
                if (res.getCount() == 0){
                    //Show Empty Message
                    showMessage("Error", "No Data is Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (res.moveToNext()){
                    buffer.append("ID :"+ res.getString(0)+"\n");
                    buffer.append("NAME :"+ res.getString(1)+"\n");
                    buffer.append("SURNAME :"+ res.getString(2)+"\n");
                    buffer.append("MARKS :"+ res.getString(3)+"\n\n");
                }
                //show all data
                showMessage("Datas",buffer.toString());
            }
        });
    }
     */
}