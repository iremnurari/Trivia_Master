package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v7.app.ActionBar;

public class AdminAddActivity extends AppCompatActivity {
    EditText question, optA, optB, optC, answer;
    Button addQuest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_add);
        final DatabaseHelper openHelper = new DatabaseHelper(this);
        question = findViewById(R.id.newQuestionTxt);
        optA = findViewById(R.id.optA);
        optB = findViewById(R.id.optB);
        optC = findViewById(R.id.optC);
        answer = findViewById(R.id.answer);
        addQuest = findViewById(R.id.addNewQuestionBtn);
        addQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!emptyFields()){
                    openHelper.addNewQuestions(question.getText().toString(),optA.getText().toString(),optB.getText().toString(),optC.getText().toString(),answer.getText().toString());
                    Intent intent = new Intent(AdminAddActivity.this,AdminActivity.class);
                    startActivity(intent);
                    Toast.makeText(getApplicationContext(), "Question added." , Toast.LENGTH_LONG).show();
                }
                else Toast.makeText(getApplicationContext(), "Cannot left empty!" , Toast.LENGTH_LONG).show();

            }
        });
    }
    private boolean emptyFields(){
        if(TextUtils.isEmpty(question.getText().toString()) || TextUtils.isEmpty(optA.getText().toString()) || TextUtils.isEmpty(optB.getText().toString()) || TextUtils.isEmpty(optC.getText().toString()) || TextUtils.isEmpty(answer.getText().toString())){
            return true;
        }
        else return false;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.AdminHome:
                goBack();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void goBack(){
        Intent i = new Intent(AdminAddActivity.this, AdminActivity.class);
        startActivity(i);
    }
}
