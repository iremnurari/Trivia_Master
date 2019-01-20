package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class QuestionEditActivity extends AppCompatActivity {
    EditText question, a, b, c, answer;
    Button updateBtn, deleteBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_edit);
        question = findViewById(R.id.editQuest);
        a = findViewById(R.id.editOptA);
        b = findViewById(R.id.editOptB);
        c = findViewById(R.id.editOptC);
        answer = findViewById(R.id.editAnswer);
        final Bundle bundle = getIntent().getExtras();
        final String cate = bundle.getString("cate");
        final int qID = bundle.getInt("qID");
        final Bundle bundle1 = new Bundle();
        bundle1.putString("c",cate);
        Log.d("selected question", ":"+cate+","+qID);
        updateBtn = findViewById(R.id.updateButton);
        deleteBtn = findViewById(R.id.deleteButton);
        FirebaseDatabase.getInstance().getReference("Questions").child(cate).child(""+qID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Question question1 = dataSnapshot.getValue(Question.class);
                question.setText(question1.getQuestion());
                a.setText(question1.getOption1());
                b.setText(question1.getOption2());
                c.setText(question1.getOption3());
                answer.setText(question1.getAnswer());
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question que = new Question();
                que.setQuestion(question.getText().toString());
                que.setOption1(a.getText().toString());
                que.setOption2(b.getText().toString());
                que.setOption3(c.getText().toString());
                que.setAnswer(answer.getText().toString());
                que.setqID(qID);
                DatabaseReference updateRef = FirebaseDatabase.getInstance().getReference("Questions").child(cate);
                updateRef.child(""+qID).setValue(que);
                Intent i = new Intent(QuestionEditActivity.this, AdminEditActivity.class);
                i.putExtras(bundle1);
                startActivity(i);
            }
        });
        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseReference deleteRef = FirebaseDatabase.getInstance().getReference("Questions").child(cate);
                deleteRef.child(""+qID).removeValue();
                Intent i = new Intent(QuestionEditActivity.this, AdminEditActivity.class);
                i.putExtras(bundle1);
                startActivity(i);
            }
        });

    }
}
