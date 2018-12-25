package com.example.nurir.trivia1;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
public class QuizActivity extends Activity {
    ArrayList<Question> quesList = null;
    int score=0;
    int qid=0;
    Question currentQ;
    TextView txtQuestion, tvUser;
    RadioButton rda, rdb, rdc;
    Button butNext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);
        final Bundle bundle = getIntent().getExtras();
        final String username = bundle.getString("user");
        final DatabaseHelper db = new DatabaseHelper(this);
        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat df = new SimpleDateFormat("dd-MMM-yyyy");
        final String date = df.format(c);
       // db.addQuestions();
        quesList = db.getAllQuestions();
        if(quesList.isEmpty()) {
            db.addQuestions();
            quesList = db.getAllQuestions();
        }
        else Log.d("error", "list is not empty");
        currentQ = quesList.get(qid);
        final int numberOfq = quesList.size();
        txtQuestion = findViewById(R.id.textView1);
        rda = findViewById(R.id.radio0);
        rdb = findViewById(R.id.radio1);
        rdc = findViewById(R.id.radio2);
        butNext = findViewById(R.id.button1);
        setQuestionView();
        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RadioGroup grp = findViewById(R.id.radioGroup1);
                RadioButton answer = findViewById(grp.getCheckedRadioButtonId());
                Log.d("yourans", currentQ.getANSWER()+" "+answer.getText());
                if(currentQ.getANSWER().equals(answer.getText()))
                {
                    score++;
                    Log.d("score", "Your score="+score);
                }
                if(!(qid >= numberOfq)){
                    currentQ=quesList.get(qid);
                    setQuestionView();
                }else{
                    Intent intent = new Intent(QuizActivity.this, ResultActivity.class);
                    Bundle b = new Bundle();
                    b.putInt("score", score); //Your score
                    b.putString("u", username);
                    Result result = new Result(date ,username,score);
                    db.saveResult(result);
                    intent.putExtras(b); //Put your score to your next Intent
                    startActivity(intent);
                    finish();

                }

            }
        });

    }

    private void setQuestionView()
    {
        txtQuestion.setText(currentQ.getQUESTION());
        rda.setText(currentQ.getOPTA());
        rdb.setText(currentQ.getOPTB());
        rdc.setText(currentQ.getOPTC());
        qid++;
    }
}
