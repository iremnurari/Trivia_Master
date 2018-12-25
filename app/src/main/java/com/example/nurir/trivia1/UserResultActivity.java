package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class UserResultActivity extends AppCompatActivity {
    ArrayList<Result> results ;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_result);
        ListView listView = findViewById(R.id.resultList);
        DatabaseHelper db = new DatabaseHelper(this);
        Bundle bundle = getIntent().getExtras();
        if(bundle !=null){
        username = bundle.getString("resultuser");
        results = db.getAllresults(username);
        }
        ResultListAdapter resultListAdapter = new ResultListAdapter(results,this);
        listView.setAdapter(resultListAdapter);

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
        Intent i = new Intent(UserResultActivity.this, HomeActivity.class);
        Bundle b = new Bundle();
        b.putString("u", username);
        i.putExtras(b);
        startActivity(i);
    }
}
