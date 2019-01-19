package com.example.nurir.trivia1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class CategoryListAdapter extends BaseAdapter {
    public ArrayList<String> adapterArrayList ;
    public Activity context;

    public CategoryListAdapter(ArrayList<String> adapterArrayList, Activity context){
        this.adapterArrayList = adapterArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return adapterArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return adapterArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final String category = (String) getItem(position);
        if(convertView==null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.cat_list_row, null);
            TextView catBtn = convertView.findViewById(R.id.catListBtn);
            catBtn.setText(category);
            return convertView;
        }
        else{
            return null;
        }

    }
}
