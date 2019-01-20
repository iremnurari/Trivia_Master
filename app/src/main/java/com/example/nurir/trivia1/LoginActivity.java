package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    public EditText emailIn, password;
    public Button signbtn, regbtn, forgotpass;
    private FirebaseAuth mAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        emailIn = findViewById(R.id.emailEditText);
        password = findViewById(R.id.passwordEditText);
        forgotpass = findViewById(R.id.btn_reset_password);
        signbtn = findViewById(R.id.signinbutton);
        regbtn = findViewById(R.id.registerButton);
        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateForm()) {
                    return;
                }
                String email = emailIn.getText().toString().trim();
                String pass = password.getText().toString().trim();

                mAuth.signInWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("LoginActivity", "signIn:onComplete:" + task.isSuccessful());

                                if (task.isSuccessful()) {
                                    onAuthSuccess(task.getResult().getUser());
                                } else {
                                    Toast.makeText(LoginActivity.this, "Sign In Failed",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!validateForm()) {
                    return;
                }
                String email = emailIn.getText().toString();
                String pass = password.getText().toString();

                mAuth.createUserWithEmailAndPassword(email, pass)
                        .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                Log.d("LoginActivity", "createUser:onComplete:" + task.isSuccessful());

                                if (task.isSuccessful()) {
                                    onAuthSuccess(task.getResult().getUser());
                                } else {
                                    Toast.makeText(LoginActivity.this, "Sign Up Failed",
                                            Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });

        forgotpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ResetPassword.class));
            }
        });
    }
    private void onAuthSuccess(FirebaseUser user) {
        String username = usernameFromEmail(user.getEmail());

        // Write new user
        writeNewUser(user.getUid(), username, user.getEmail());

        // Go to MainActivity
        Bundle bundle = new Bundle();
        bundle.putString("username", username);
        Intent intent = new Intent(LoginActivity.this, HomeActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
        finish();
    }

    //username is email until @
    private String usernameFromEmail(String email) {
        if (email.contains("@")) {
            return email.split("@")[0];
        } else {
            return email;
        }
    }

    private void writeNewUser(String userId, String name, String email) {
        Users user = new Users(name, email);
        databaseReference.child("Users").child(userId).setValue(user);
    }

    private boolean validateForm() {
        boolean result = true;
        if (TextUtils.isEmpty(emailIn.getText().toString())) {
            emailIn.setError("Required");
            result = false;
        } else {
            emailIn.setError(null);
        }

        if (TextUtils.isEmpty(password.getText().toString())) {
            password.setError("Required");
            result = false;
        } else {
            password.setError(null);
        }

        return result;
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.adminLogin:
                admin();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void admin(){
        startActivity(new Intent(LoginActivity.this, AdminLogin.class));
        finish();
    }


}
