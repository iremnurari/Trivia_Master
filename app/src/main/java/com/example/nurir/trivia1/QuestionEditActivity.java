package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class QuestionEditActivity extends AppCompatActivity {
    EditText question, a, b, c, answer;
    Button updateBtn, deleteBtn;
    Question que = new Question();
    DatabaseHelper databaseHelper = new DatabaseHelper(this);
    int ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_edit);
        question = findViewById(R.id.editQuest);
        a = findViewById(R.id.editOptA);
        b = findViewById(R.id.editOptB);
        c = findViewById(R.id.editOptC);
        answer = findViewById(R.id.editAnswer);
        updateBtn = findViewById(R.id.updateButton);
        deleteBtn = findViewById(R.id.deleteButton);
        Bundle bundle = getIntent().getExtras();
        if(bundle != null){
            ID = bundle.getInt("ID");
        }
        que = databaseHelper.findQuestion(ID);
        if(que == null ) Log.d("error", "couldn't find the object");
        question.setText(que.getQUESTION());
        a.setText(que.getOPTA());
        b.setText(que.getOPTB());
        c.setText(que.getOPTC());
        answer.setText(que.getANSWER());
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                que.setQUESTION(question.getText().toString());
                que.setOPTA(a.getText().toString());
                que.setOPTB(b.getText().toString());
                que.setOPTC(c.getText().toString());
                que.setANSWER(answer.getText().toString());
                databaseHelper.editQuestion(que);
                Intent i = new Intent(QuestionEditActivity.this, AdminEditActivity.class);
                startActivity(i);
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteQuestion(ID);
                Intent i = new Intent(QuestionEditActivity.this, AdminEditActivity.class);
                startActivity(i);
            }
        });

    }
}
