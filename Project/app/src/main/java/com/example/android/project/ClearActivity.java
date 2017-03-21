package com.example.android.project;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class ClearActivity extends AppCompatActivity {
    private ArrayList<ShoppingItem> items;
    private ItemAdapter itemAdapter;
    private ListView lv;
    private ShoppingDataSource dataSource;
    //creates string to pass back to main Activity
    private String itemClear = "";
    //counter to keep track of items deleted
    private int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clear);
        dataSource = new ShoppingDataSource(this);
        dataSource.open();

        items = dataSource.getAll();
        //check size of list to see if any items exist
        if(items.size() == 0){
            itemClear = "No items in the database";
            //return to Main
            finish();
        } else {
            itemAdapter = new ItemAdapter(this, items);
            lv = (ListView) findViewById(R.id.clearLV);
            lv.setAdapter(itemAdapter);
            lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 ShoppingItem item = (ShoppingItem) parent.getItemAtPosition(position);
                    dataSource.open();
                    dataSource.delete(item);
                    dataSource.close();

                    count++;
                    itemClear = count + " items were Deleted!";
                    updateList();

                    }
            });
        }
        dataSource.close();
    }

    @Override
    public void finish() {
        Intent intent = new Intent();
        intent.putExtra("itemClear", itemClear);
        setResult(RESULT_OK, intent);
        super.finish();
    }

    public void clearAllItems(View view) {
        dataSource.open();
        dataSource.deleteAll();
        dataSource.close();
        itemClear = "All items Deleted!!";
        finish();

    }

    private void updateList(){
        //refreshes the list in listView
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
