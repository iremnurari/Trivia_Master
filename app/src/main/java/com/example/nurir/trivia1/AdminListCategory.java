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
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AdminListCategory extends AppCompatActivity {
    ArrayList<String> categoryArrayList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_list_category);
        final Bundle adminBundle = getIntent().getExtras();
        final ListView catLV = findViewById(R.id.catListView);
        categoryArrayList = new ArrayList<>();
        FirebaseDatabase.getInstance().getReference("Questions").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    //get question child's keys only
                    String key = snapshot.getKey();
                    categoryArrayList.add(key);
                }
                CategoryListAdapter categoryListAdapter = new CategoryListAdapter(categoryArrayList,AdminListCategory.this);
                catLV.setAdapter(categoryListAdapter);
                catLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        String c1 = categoryArrayList.get(position);
                        final Bundle bundle = new Bundle();
                        Log.d("category clicked", ":"+c1);
                        bundle.putString("c",c1);
                        if(adminBundle != null){
                            Intent i = new Intent(AdminListCategory.this, AdminAddActivity.class);
                            i.putExtras(bundle);
                            startActivity(i);
                        }
                        else{
                            Intent i = new Intent(AdminListCategory.this, AdminEditActivity.class);
                            i.putExtras(bundle);
                            startActivity(i);
                        }

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_category, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.add_cat:
                startActivity(new Intent(AdminListCategory.this, AddCategoryActivity.class));
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
