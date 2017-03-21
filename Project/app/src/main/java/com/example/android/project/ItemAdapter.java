package com.example.android.project;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Joe Bonifacio on 9/03/2017.
 */

public class ItemAdapter extends ArrayAdapter<ShoppingItem>  {

    public ItemAdapter(Context context, ArrayList<ShoppingItem> shoppingItems){
        super (context,0,shoppingItems);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ShoppingItem item = getItem(position);
        //check to see if view exists, if so it is recycled, if not it is inflated
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.row_layout, parent, false);
        }
        TextView name = (TextView)convertView.findViewById(R.id.itemNameTV);
        TextView quantity = (TextView)convertView.findViewById(R.id.itemQuantityTV);
        CheckBox isPurchased = (CheckBox)convertView.findViewById(R.id.purchasedCB);


        name.setText(item.getName());
        quantity.setText(item.getQuantity() + "");
        if(item.getIsPurchased() == 1){
            isPurchased.setChecked(true);
        }else{
            isPurchased.setChecked(false);
        }


        return convertView;



    }


}
