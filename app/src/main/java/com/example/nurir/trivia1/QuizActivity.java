package com.example.nurir.trivia1;

import android.graphics.Color;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuizActivity extends Activity {

    int score=0;
    int total = 0;
    int i=0;
    int child;
    ArrayList<Question> questions;
    TextView txtQuestion, tvUser,countQ;
    Button rda, rdb, rdc;
    DatabaseReference questionDatabase;

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        questions = new ArrayList<>();
        txtQuestion = findViewById(R.id.questTitle);
        rda = findViewById(R.id.radio0);
        rdb = findViewById(R.id.radio1);
        rdc = findViewById(R.id.radio2);
        countQ = findViewById(R.id.countQ);

        updateQuestion();

    }

    public void updateQuestion(){
        total++;
        final String ct = total+ " / 5";
        Bundle b = getIntent().getExtras();
        final String cat = b.getString("cat");
        ArrayList<Integer> rands = b.getIntegerArrayList("rands");
                if(total>5){
                    //result activity
                    countQ.setText(R.string.finished);
                    Bundle bundle = new Bundle();
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    bundle.putInt("score", score);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
                else{
                    child = rands.get(i);
                    i++;
                    countQ.setText(ct);
                    questionDatabase = FirebaseDatabase.getInstance().getReference("Questions").child(cat).child(String.valueOf(child));
                    questionDatabase.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            final Question question = dataSnapshot.getValue(Question.class);
                            txtQuestion.setText(question.getQuestion());
                            rda.setText(question.getOption1());
                            rdb.setText(question.getOption2());
                            rdc.setText(question.getOption3());
                            rda.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(rda.getText().toString().equals(question.getAnswer())){
                                        rda.setBackgroundColor(Color.GREEN);
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                score++;
                                                rda.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                                                updateQuestion();
                                            }
                                        },1500);

                                    }
                                    else{
                                        rda.setBackgroundColor(Color.RED);
                                        if(rdb.getText().toString().equals(question.getAnswer())){
                                            rdb.setBackgroundColor(Color.GREEN);
                                        }
                                        else if(rdc.getText().toString().equals(question.getAnswer())){
                                            rdc.setBackgroundColor(Color.GREEN);
                                        }
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                rda.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                                                rdb.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                                                rdc.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                                                updateQuestion();
                                            }
                                        },1500);

                                    }

                                }
                            });
                            rdb.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(rdb.getText().toString().equals(question.getAnswer())){
                                        rdb.setBackgroundColor(Color.GREEN);
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                score++;
                                                rdb.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                                                updateQuestion();
                                            }
                                        },1500);

                                    }
                                    else{
                                        rdb.setBackgroundColor(Color.RED);
                                        if(rda.getText().toString().equals(question.getAnswer())){
                                            rda.setBackgroundColor(Color.GREEN);
                                        }
                                        else if(rdc.getText().toString().equals(question.getAnswer())){
                                            rdc.setBackgroundColor(Color.GREEN);
                                        }
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                rda.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                                                rdb.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                                                rdc.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                                                updateQuestion();
                                            }
                                        },1500);

                                    }
                                }
                            });
                            rdc.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if(rdc.getText().toString().equals(question.getAnswer())){
                                        rdc.setBackgroundColor(Color.GREEN);
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                score++;
                                                rdc.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                                                updateQuestion();
                                            }
                                        },1500);

                                    }
                                    else{
                                        rdc.setBackgroundColor(Color.RED);
                                        if(rdb.getText().toString().equals(question.getAnswer())){
                                            rdb.setBackgroundColor(Color.GREEN);
                                        }
                                        else if(rda.getText().toString().equals(question.getAnswer())){
                                            rda.setBackgroundColor(Color.GREEN);
                                        }
                                        Handler handler = new Handler();
                                        handler.postDelayed(new Runnable() {
                                            @Override
                                            public void run() {
                                                rda.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                                                rdb.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                                                rdc.setBackgroundColor(getResources().getColor(R.color.buttonColor));
                                                updateQuestion();
                                            }
                                        },1500);

                                    }
                                }
                            });

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
            }





}
