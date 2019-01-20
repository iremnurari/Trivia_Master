package com.example.nurir.trivia1;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class ResultActivity extends AppCompatActivity {
    DatabaseReference resultDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        RatingBar bar = findViewById(R.id.ratingBar1);
        bar.setNumStars(5);
        bar.setStepSize(0.5f);
        //get text view
        TextView t = findViewById(R.id.textResult);
        //get score
        Bundle b = getIntent().getExtras();
        assert b != null;
        int score= b.getInt("score");
        //display score
        t.setText(MessageFormat.format("your score is {0}", score));
        bar.setRating(score);
        saveRecord(score);

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
        Intent i = new Intent(ResultActivity.this, HomeActivity.class);
        startActivity(i);
    }
    public void saveRecord(int s){
        Date c = Calendar.getInstance().getTime();
        @SuppressLint("SimpleDateFormat") SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
        final String date = df.format(c);
        String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();

        resultDatabase = FirebaseDatabase.getInstance().getReference("Result");
        String id = resultDatabase.push().getKey();
        Result result = new Result(id, date, userID, s);
        resultDatabase.child(id).setValue(result);


    }


}
