package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminEditActivity extends AppCompatActivity {
    ArrayList<Question> questions;
    int qID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_edit);
        final ListView listView = findViewById(R.id.questList);
        Bundle b = getIntent().getExtras();
        final String cat = b.getString("c");
        questions = new ArrayList<>();
        //add questions to arr
        FirebaseDatabase.getInstance().getReference("Questions").child(cat).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot qSnapshot : dataSnapshot.getChildren()){
                    Question question = qSnapshot.getValue(Question.class);
                    questions.add(question);
                }
                QuestionListAdapter listAdapter = new QuestionListAdapter(questions,AdminEditActivity.this);
                listView.setAdapter(listAdapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Question q1 = questions.get(position);
                        Bundle bundle = new Bundle();
                        qID = q1.getqID();
                        bundle.putString("cate", cat);
                        bundle.putInt("qID", qID);
                        Intent i = new Intent(AdminEditActivity.this, QuestionEditActivity.class);
                        i.putExtras(bundle);
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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
        Intent i = new Intent(AdminEditActivity.this, AdminActivity.class);
        startActivity(i);
    }
}
