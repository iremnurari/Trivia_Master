package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView tvUser;
    Button startQ, result;
    String username, welcomeMsg;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvUser = findViewById(R.id.tvUser);
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            username = bundle.getString("user");
            if(username==null){
                username = bundle.getString("u");
            }
            welcomeMsg = "Welcome "+username+".";
            tvUser.setText(welcomeMsg);
        }
        startQ = findViewById(R.id.startBtn);
        startQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
                Bundle bundle1= new Bundle();
                bundle1.putString("user", username);
                intent.putExtras(bundle1);
                startActivity(intent);
            }
        });
        result = findViewById(R.id.resultBtn);
        result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeActivity.this, UserResultActivity.class);
                Bundle bundle2 = new Bundle();
                bundle2.putString("resultuser", username);
                i.putExtras(bundle2);
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
        Intent i = new Intent(HomeActivity.this, LoginActivity.class);
        startActivity(i);
    }

}
