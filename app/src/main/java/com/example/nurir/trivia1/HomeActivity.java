package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class HomeActivity extends AppCompatActivity {
    TextView tvUser;
    Button startQ, result;
    String welcomeMsg;
    FirebaseAuth auth;
    Spinner spn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvUser = findViewById(R.id.tvUser);
        startQ = findViewById(R.id.startQBtn);
        auth = FirebaseAuth.getInstance();
        final String userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        FirebaseDatabase.getInstance().getReference("Users").child(userID)
                .addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Users user = dataSnapshot.getValue(Users.class);
                String username = user.getUSERNAME();
                welcomeMsg = "Welcome "+username+".";
                tvUser.setText(welcomeMsg);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Log.d("Database error: ", "failed to load data");
            }
        });

        spn = findViewById(R.id.spinner);
        final List<String> arr = new ArrayList<String>();
        arr.add(0,"Choose Category");
        FirebaseDatabase.getInstance().getReference("Questions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //get question child's keys only
                    String key = snapshot.getKey();
                    arr.add(key);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>( this, android.R.layout.simple_spinner_item, arr);
        spn.setAdapter(arrayAdapter);
        spn.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getItemAtPosition(position).equals("Choose Category")){

                }
                else {
                    String item = parent.getItemAtPosition(position).toString();
                    Toast.makeText(parent.getContext(), ""+item, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        startQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cat = spn.getSelectedItem().toString();
                if(cat.equals("Choose Category")){
                    Toast.makeText(getApplicationContext(), "Please select a category.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Log.d("category", ":" + cat);
                    Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
                    Bundle b = new Bundle();
                    b.putString("cat", cat);
                    intent.putExtras(b);
                    startActivity(intent);
                }
            }
        });
        result = findViewById(R.id.resultBtn);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, UserResultActivity.class);
                startActivity(i);
            }
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_exit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.HomeExit:
                goBack();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void goBack(){
        auth.signOut();
        startActivity(new Intent(HomeActivity.this, LoginActivity.class));

    }

}
