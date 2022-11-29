package com.example.nftmarketplace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nftmarketplace.adapters.NFTAdapter;
import com.example.nftmarketplace.models.NFTModel;
import com.example.nftmarketplace.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.List;

public class Profile extends AppCompatActivity
{
    BottomNavigationView bottomNavigationView;
    DBHelper db;
    ImageView userIcon;
    TextView username, emailAdress;
    RecyclerView nftCollection;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setSelectedItemId(R.id.profile);
        userIcon = findViewById(R.id.userIcon);
        username = findViewById(R.id.username);
        emailAdress = findViewById(R.id.emailAdress);
        db = new DBHelper(this);
        Intent intent = getIntent();



        User userProfile = db.getUser(intent.getStringExtra("username"));

        userIcon.setImageResource(userProfile.getUserLogo());
        username.setText(userProfile.getUserName());
        emailAdress.setText(userProfile.getEmail());


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
                        finish();
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

        List<NFTModel> nftList = db.getNftByUser(intent.getStringExtra("username"));

        NFTModel[] nfts = nftList.toArray(new NFTModel[nftList.size()]);

        Log.i("size", String.valueOf(nftList.size()));

        nftCollection = findViewById(R.id.nftCollection);
        NFTAdapter nftAdapter = new NFTAdapter(nfts,this);
        nftCollection.setHasFixedSize(true);
        nftCollection.setLayoutManager(new GridLayoutManager(this,2));
        nftCollection.setAdapter(nftAdapter);
    }

}