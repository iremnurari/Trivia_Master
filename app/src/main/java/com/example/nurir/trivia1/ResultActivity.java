package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RatingBar;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {
    String u;
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
        int score= b.getInt("score");
        u = b.getString("u");
        //display score
        t.setText("your score is " +score);
        bar.setRating(score);

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
        Bundle bundle = new Bundle();
        bundle.putString("u", u );
        i.putExtras(bundle);
        startActivity(i);
    }
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.activity_result, menu);
        return true;
    }*/

}
