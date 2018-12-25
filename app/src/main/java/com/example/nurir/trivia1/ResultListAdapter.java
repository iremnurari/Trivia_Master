package com.example.nurir.trivia1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ResultListAdapter extends BaseAdapter {
    public ArrayList<Result> adapterArrayList ;
    public Activity context;

    public ResultListAdapter(ArrayList<Result> adapterArrayList, Activity context){
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
        final Result result = (Result) getItem(position);
        if(convertView == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.result_list_row, null);
            TextView resultTxt = convertView.findViewById(R.id.resultRow);
            TextView idResult = convertView.findViewById(R.id.num);
            resultTxt.setText("--"+result.getScore());
            idResult.setText(result.getDate());
            return convertView;
        }
        else {
            return null;
        }
    }
}
