package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class AdminEditActivity extends AppCompatActivity {
    ArrayList<Question> questions;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit);
        ListView listView = findViewById(R.id.questList);
        DatabaseHelper openHelper = new DatabaseHelper(this);
        questions = openHelper.getAllQuestions();
        if(questions.isEmpty()){
            openHelper.addQuestions();
            questions = openHelper.getAllQuestions();
        }
        QuestionListAdapter listAdapter = new QuestionListAdapter(questions,this);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(AdminEditActivity.this, QuestionEditActivity.class);
                Bundle bundle = new Bundle();
                Question question = questions.get(position);
                bundle.putInt("ID", question.getID());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}
