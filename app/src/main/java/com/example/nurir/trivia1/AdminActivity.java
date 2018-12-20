package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {
    TextView welcome;
    Button ADD, REMOVE, EDIT;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        welcome = findViewById(R.id.adminWelcomeTxt);
        ADD = findViewById(R.id.adminAddBtn);
        ADD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AdminAddActivity.class);
                startActivity(intent);
            }
        });
       // REMOVE = findViewById(R.id.adminRemoveBtn);
        EDIT = findViewById(R.id.adminEditBtn);
        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AdminEditActivity.class);
                startActivity(intent);
            }
        });
        welcome.setText("Welcome admin" );

    }
}
