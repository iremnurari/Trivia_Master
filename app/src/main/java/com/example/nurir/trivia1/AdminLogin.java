package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;

public class AdminLogin extends AppCompatActivity {
    private FirebaseAuth auth;
    private EditText adminEmailTxt, adminPassTxt;
    private Button logBtn, backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);
        auth = FirebaseAuth.getInstance();

        adminEmailTxt = findViewById(R.id.adminEditText);
        adminPassTxt = findViewById(R.id.admin_passwordEditText);

        logBtn = findViewById(R.id.adminSignIn);
        backBtn = findViewById(R.id.backBtn);
        logBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String em = adminEmailTxt.getText().toString();
                String ps = adminPassTxt.getText().toString();
                final String adminUid = "lRXimcsi1pbdkB1TGGaksYGY4C52";
                if(!validateForm()){
                    return;
                }

                auth.signInWithEmailAndPassword(em,ps).addOnCompleteListener(AdminLogin.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        String curUid = FirebaseAuth.getInstance().getCurrentUser().getUid();
                        if(curUid.equals(adminUid)){
                            startActivity(new Intent(AdminLogin.this, AdminActivity.class));
                        }
                        else{
                            Toast.makeText(getApplicationContext(), "If you are not admin please go back", Toast.LENGTH_LONG).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Sign in failed", Toast.LENGTH_LONG).show();
                    }
                    }
                });
            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminLogin.this, LoginActivity.class));
            }
        });
    }
    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(adminEmailTxt.getText().toString())) {
            adminEmailTxt.setError("Required");
            result = false;
        } else {
            adminEmailTxt.setError(null);
        }

        if (TextUtils.isEmpty(adminPassTxt.getText().toString())) {
            adminPassTxt.setError("Required");
            result = false;
        } else {
            adminPassTxt.setError(null);
        }

        return result;
    }
}
