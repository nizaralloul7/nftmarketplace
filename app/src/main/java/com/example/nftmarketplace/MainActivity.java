package com.example.nftmarketplace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import com.example.nftmarketplace.adapters.NFTAdapter;
import com.example.nftmarketplace.adapters.UsersAdapter;
import com.example.nftmarketplace.models.NFTModel;
import com.example.nftmarketplace.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity
{
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        Log.i("My Logger",sharedpreferences.getString("userName","error"));
        User user1 = new User("Nizar", "Alloul", "Shigur","motdepasse123","email",14.55,R.drawable.hape);
        User user2 = new User("Othmane", "Lz", "TfBlade","motdepasse123","email",14.55,R.drawable.overstudy_g1);
        User user3 = new User("Mounia", "Lz", "MouniaEch","motdepasse123","email",21.55,R.drawable.anothermonkey);

        NFTModel[] nftModels = new NFTModel[]
                {
                        new NFTModel("3.14ETH", "2477USD", R.drawable.ryden_g2,"RydenX1-22",user1,"This Nft is ..."),
                        new NFTModel("3.18ETH", "2500USD", R.drawable.ryden,"RydenX2-28",user2,"This Nft is ..."),
                        new NFTModel("4.51ETH", "3100USD", R.drawable.overstudy_g1,"OverStudy-5v2",user1,"This Nft is ..."),
                        new NFTModel("4.55ETH", "3254USD", R.drawable.arthilarius_g1,"ArtHilarious-g2",user2,"This Nft is ..."),
                };

        User[] users = new User[]
                {
                        user1,
                        user2,
                        user3
                };

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.logout :
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home :
                        return true;
                    case R.id.bids :
                        return true;
                    case R.id.users :
                        return true;
                    case R.id.profile :
                        startActivity(new Intent(MainActivity.this, Profile.class));
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });

        RecyclerView recyclerViewNfts = findViewById(R.id.recyclerViewNfts);
        NFTAdapter nftAdapter = new NFTAdapter(nftModels,this);
        recyclerViewNfts.setHasFixedSize(true);
        recyclerViewNfts.setLayoutManager(new GridLayoutManager(this,2));
        recyclerViewNfts.setAdapter(nftAdapter);

        RecyclerView recyclerViewUsers = findViewById(R.id.topCreators);
        UsersAdapter usersAdapter = new UsersAdapter(users, this);
        recyclerViewUsers.setHasFixedSize(true);
        recyclerViewUsers.setLayoutManager(new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false));
        recyclerViewUsers.setAdapter(usersAdapter);

    }
}