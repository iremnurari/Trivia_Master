package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView tvUser;
    Button startQ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        tvUser = findViewById(R.id.tvUser);
        final Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            tvUser.setText(bundle.getString("user"));
        }
        startQ = findViewById(R.id.startBtn);
        startQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, QuizActivity.class);
                //intent.putExtras(bundle);
                startActivity(intent);
            }
        });



    }

}
