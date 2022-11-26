package com.example.nftmarketplace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Profile extends AppCompatActivity
{
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setSelectedItemId(R.id.profile);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.logout :
                        startActivity(new Intent(Profile.this, LoginActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home :
                        startActivity(new Intent(Profile.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bids :
                        return true;
                    case R.id.users :
                        return true;
                    case R.id.profile :
                        return true;
                }

                return false;
            }
        });
    }
}