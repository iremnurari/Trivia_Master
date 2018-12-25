package com.example.nurir.trivia1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class QuestionListAdapter extends BaseAdapter {
    public ArrayList <Question> adapterArrayList ;
    public Activity context;

    public QuestionListAdapter(ArrayList<Question> adapterArrayList, Activity context){
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
        final Question question = (Question) getItem(position);
        if(convertView == null){
            LayoutInflater layoutInflater = context.getLayoutInflater();
            convertView = layoutInflater.inflate(R.layout.edit_list_row, null);
            TextView titleQuestion = convertView.findViewById(R.id.questionTitle);
            TextView idQuestion = convertView.findViewById(R.id.questionID);
            titleQuestion.setText(question.getQUESTION());
            idQuestion.setText(+question.getID()+" :");
            return convertView;
        }
        else {
            return null;
        }
    }
}
