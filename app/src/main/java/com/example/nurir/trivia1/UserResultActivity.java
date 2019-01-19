package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserResultActivity extends AppCompatActivity {
    ArrayList<Result> results ;
    FirebaseAuth auth;
    DatabaseReference resultDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_result);
        final ListView listView = findViewById(R.id.resultList);
        String uID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("userID: ", " "+uID);
        results = new ArrayList<>();
        resultDatabase = FirebaseDatabase.getInstance().getReference();
        //get only current users results
        resultDatabase.child("Result").orderByChild("userID").equalTo(uID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot resSnap : dataSnapshot.getChildren()){
                    Result result = resSnap.getValue(Result.class);
                    results.add(result);
                }
                ResultListAdapter resultListAdapter = new ResultListAdapter(results,UserResultActivity.this);
                listView.setAdapter(resultListAdapter);
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
       startActivity(new Intent(UserResultActivity.this, HomeActivity.class));
    }
}
