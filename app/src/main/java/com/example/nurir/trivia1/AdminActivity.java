package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AdminActivity extends AppCompatActivity {
    TextView welcome;
    Button ADD, EDIT;
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
        EDIT = findViewById(R.id.adminEditBtn);
        EDIT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AdminListCategory.class);
                startActivity(intent);
            }
        });
        welcome.setText("Welcome admin" );


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
        Intent i = new Intent(AdminActivity.this, LoginActivity.class);
        startActivity(i);
    }
}
