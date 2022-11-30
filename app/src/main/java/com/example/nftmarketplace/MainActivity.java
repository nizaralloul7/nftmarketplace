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

import java.util.List;

public class MainActivity extends AppCompatActivity
{
    BottomNavigationView bottomNavigationView;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = new DBHelper(this);

        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setSelectedItemId(R.id.home);
        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        User user1 = new User("Nizar", "Alloul", "Shigur","motdepasse123","nizaralloul7@gmail.com",120.04,R.drawable.profile1);
        User user2 = new User("Othmane", "Lz", "TfBlade","motdepasse123","othmane@gmaile.com",111.00,R.drawable.profile2);
        User user3 = new User("Mounia", "Ech", "MEchel","motdepasse123","mounia@gmail.com",314.54,R.drawable.profile3);
        User user4 = new User("Basma", "Haimeur", "Smilie","motdepasse123","basma.haimeur@gmail.com",102.01,R.drawable.anothermonkey);
        User user5 = new User("Yahya", "Bjrh", "Ybjrh","motdepasse123","Yahya.boujrah@gmail.com",87.14,R.drawable.profile4);

        User[] users = new User[]
                {
                        user1,
                        user2,
                        user3,
                        user4,
                        user5
                };

        List<NFTModel> nftList  = db.getNfts();

        NFTModel[] nfts =nftList.toArray(new NFTModel[nftList.size()]);



        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.logout :
                        finish();
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home :
                    case R.id.addnft:
                        return true;
                    case R.id.bids :
                        startActivity(new Intent(MainActivity.this, BidsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.profile :
                        Intent i = new Intent(MainActivity.this, Profile.class);
                        i.putExtra("username",sharedpreferences.getString("userName","error") );
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });

        RecyclerView recyclerViewNfts = findViewById(R.id.recyclerViewNfts);
        NFTAdapter nftAdapter = new NFTAdapter(nfts,this);
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