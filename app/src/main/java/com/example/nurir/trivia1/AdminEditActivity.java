package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class AdminEditActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit);
        ListView listView = findViewById(R.id.questList);
        ArrayList<Question> questions;
        DatabaseHelper openHelper = new DatabaseHelper(this);
        questions = openHelper.getAllQuestions();
        QuestionListAdapter listAdapter = new QuestionListAdapter(questions,this);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AdminEditActivity.this, QuestionEditActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("ID", position+1);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
