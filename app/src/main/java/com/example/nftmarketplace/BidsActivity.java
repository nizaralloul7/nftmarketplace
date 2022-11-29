package com.example.nftmarketplace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.nftmarketplace.adapters.BidsAdapter;
import com.example.nftmarketplace.models.Bid;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class BidsActivity extends AppCompatActivity
{
    DBHelper dbHelper;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bids);

        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setSelectedItemId(R.id.bids);
        dbHelper = new DBHelper(this);

        Bid[] bids = dbHelper.getBidsByUser(sharedpreferences.getString("userName","error"));
        BidsAdapter bidsAdapter;

        if(bids.length == 0)
        {
             bidsAdapter = new BidsAdapter(new Bid[0],this,sharedpreferences.getString("userName","error"));
        }
        else
             bidsAdapter = new BidsAdapter(bids,this,sharedpreferences.getString("userName","error"));


        RecyclerView recyclerView = findViewById(R.id.recyClerNotifs);


        recyclerView.setHasFixedSize(false);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        recyclerView.setAdapter(bidsAdapter);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.logout :
                        startActivity(new Intent(BidsActivity.this, LoginActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.home :
                        startActivity(new Intent(BidsActivity.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bids :
                        return true;
                    case R.id.users :
                        return true;
                    case R.id.profile :
                        Intent i = new Intent(BidsActivity.this, Profile.class);
                        i.putExtra("username",sharedpreferences.getString("userName","error") );
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });
    }
}