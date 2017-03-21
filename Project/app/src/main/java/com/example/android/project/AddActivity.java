package com.example.android.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {
    //Create string to pass back to main activity
private String itemConfirm = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        //adds string to intent to pass back on finish
        intent.putExtra("itemConfirm", itemConfirm);
        setResult(RESULT_OK, intent);


        super.finish();
    }

    public void addItem(View view) {
        EditText nameEt = (EditText)findViewById(R.id.nameEditText);
        EditText quantityEt = (EditText)findViewById(R.id.quantityEditText);
        ShoppingDataSource dataSource = new ShoppingDataSource(this);
        //checks length of Edit texts to make sure not empty
        if(nameEt.length() != 0 && quantityEt.length() !=0){
            String name = nameEt.getText().toString();
            int quantity = Integer.parseInt(quantityEt.getText().toString());
            itemConfirm = name + " has been added!";
            dataSource.open();
            dataSource.insert(name,quantity,0);
            dataSource.close();
            finish();

        }else{
            Toast.makeText(this, "Please fill out all Fields!", Toast.LENGTH_SHORT).show();
        }


    }
}
