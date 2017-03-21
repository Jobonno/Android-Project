package com.example.android.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
public static final int REQUEST_CODE = 1;
public String msg;
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE){
            if(data.hasExtra("itemConfirm")) {
                msg = data.getStringExtra("itemConfirm");
                //only display toast if msg has length
                if (msg.length() > 0) {
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                }

            }else if(data.hasExtra("itemClear")){
                msg = data.getStringExtra("itemClear");
                //only display toast if msg has length
                if (msg.length() > 0) {
                    Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
                }


            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflate the view
        setContentView(R.layout.activity_main);
    }

    public void navigate(View view) {
        switch (view.getId()){
            case R.id.addBtn: Intent intentAdd = new Intent(this, AddActivity.class);
                startActivityForResult(intentAdd, REQUEST_CODE);
                break;
            case R.id.viewBtn: Intent intentView = new Intent(this, ViewActivity.class);
                startActivity(intentView);
                break;

            case R.id.removeBtn:Intent intentClear = new Intent(this, ClearActivity.class);
                startActivityForResult(intentClear, REQUEST_CODE);
                break;
        }
    }
}
