package com.example.android.project;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ViewActivity extends AppCompatActivity {
    private ArrayList<ShoppingItem> items;
    private ItemAdapter itemAdapter;
     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        final ShoppingDataSource dataSource = new ShoppingDataSource(this);
         dataSource.open();

         items = dataSource.getAll();
         if(items.size() == 0){
             Toast.makeText(this,"No Items in the database", Toast.LENGTH_SHORT).show();
             finish();
         } else {
             itemAdapter = new ItemAdapter(this, items);
             final ListView lv = (ListView) findViewById(R.id.mainLV);
             lv.setAdapter(itemAdapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        ShoppingItem item = (ShoppingItem) parent.getItemAtPosition(position);
                        dataSource.open();
                        dataSource.Update(item);
                        dataSource.close();
                        updateList();
                    }
                });
             }
         dataSource.close();

    }

    private void updateList(){
        itemAdapter.clear();
        ShoppingDataSource dataSource = new ShoppingDataSource(this);
        dataSource.open();
        for(ShoppingItem item : dataSource.getAll()){
            itemAdapter.add(item);
        }

        dataSource.close();
        itemAdapter.notifyDataSetChanged();
    }
}
