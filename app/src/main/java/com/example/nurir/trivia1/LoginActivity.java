package com.example.nurir.trivia1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    public EditText username, password;
    public Button signbtn, regbtn;
    public String adminUsername = "admin";
    public String adminPassword = "1111";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final DatabaseHelper openHelper = new DatabaseHelper(this);
        username = findViewById(R.id.userNameEditText);
        password = findViewById(R.id.passwordEditText);
        signbtn = findViewById(R.id.signinbutton);
        regbtn = findViewById(R.id.registerButton);
        signbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!emptyFields()) {
                    Users user = openHelper.searchUser(username.getText().toString(), password.getText().toString());
                    if(admin()){
                        //Bundle bundle = new Bundle();
                       // bundle.putString("admin", adminUsername);
                        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                        //intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Welcome " +adminUsername , Toast.LENGTH_SHORT).show();
                    }
                    if (user != null)
                    {
                        Bundle bundle = new Bundle();
                        bundle.putString("user", user.getUSERNAME());
                        Intent intent = new Intent(LoginActivity.this,HomeActivity.class);
                        intent.putExtras(bundle);
                        startActivity(intent);
                        Toast.makeText(getApplicationContext(), "Welcome " + user.getUSERNAME(), Toast.LENGTH_SHORT).show();
                    }
                    if(!admin() && user==null)
                        {
                        Toast.makeText(getApplicationContext(), "You are not registered", Toast.LENGTH_LONG).show();
                        }
                }
                else
                    Toast.makeText(getApplicationContext(), "Can not left empty", Toast.LENGTH_LONG).show();
            }
        });

        regbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!emptyFields()) {
                    Users user = new Users(username.getText().toString(), password.getText().toString());
                    openHelper.addUser(user);
                    Toast.makeText(getApplicationContext(), "Registered Successfully", Toast.LENGTH_LONG).show();
                    username.setText("");
                    password.setText("");
                }
                else
                    Toast.makeText(getApplicationContext(), "Can not left empty", Toast.LENGTH_LONG).show();
            }
        });
    }
    public  boolean admin(){
        if((username.getText().toString().equals(adminUsername) ) && (password.getText().toString().equals(adminPassword)))
        {
            return true;
        }
        else return false;
    }

    private boolean emptyFields(){
        if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(password.getText().toString())){
            return true;
        }
        else return false;
    }

    /*public void insertUser(String u, String p){
        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.U_uname, u);
        values.put(DatabaseHelper.U_password, p);
        long id = db.insert(DatabaseHelper.T_User, null,values);
    }*/
}
