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

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCategoryActivity extends AppCompatActivity {
    EditText question, optA, optB, optC, answer, ID, category;
    Button addQuest;
    DatabaseReference databaseReference;
    Long id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_category);
        category = findViewById(R.id.newCatTxt);
        ID = findViewById(R.id.CnewQID);
        question = findViewById(R.id.CnewQuestionTxt);
        optA = findViewById(R.id.CoptA);
        optB = findViewById(R.id.CoptB);
        optC = findViewById(R.id.CoptC);
        answer = findViewById(R.id.Canswer);
        databaseReference = FirebaseDatabase.getInstance().getReference("Questions");
        addQuest = findViewById(R.id.addNewCatBtn);
        addQuest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!emptyFields()){
                    String childID = ID.getText().toString();
                    int qID = Integer.valueOf(childID);
                    String cat = category.getText().toString();
                    Question q = new Question(question.getText().toString(),optA.getText().toString(),optB.getText().toString(),optC.getText().toString(),answer.getText().toString(),qID);
                    databaseReference.child(cat).child(childID).setValue(q);
                    startActivity(new Intent(AddCategoryActivity.this, AdminActivity.class));
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
        Intent i = new Intent(AddCategoryActivity.this, AdminActivity.class);
        startActivity(i);
    }
}
