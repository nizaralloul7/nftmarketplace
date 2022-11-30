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

        Log.i("My Logger",sharedpreferences.getString("userName","error"));
        User user1 = new User("Nizar", "Alloul", "Shigur","motdepasse123","email",14.55,R.drawable.hape);
        User user2 = new User("Othmane", "Lz", "TfBlade","motdepasse123","email",14.55,R.drawable.overstudy_g1);
        User user3 = new User("Mounia", "Lz", "MouniaEch","motdepasse123","email",21.55,R.drawable.anothermonkey);
/*
        db.insertUser(user1);
        db.insertUser(user2);
        db.insertUser(user3);

        User user = db.getUser("test");

        NFTModel[] nftModels = new NFTModel[]
                {
                        new NFTModel("3.18", "3714,40USD", R.drawable.nft1,"RydenHat-15",user,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("4.85", "5665,04USD", R.drawable.nft2,"RydenSam-05",user2,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("8.01", "9356,08USD", R.drawable.nft3,"JhinGlas-X",user,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("2.54", "2966,85USD", R.drawable.nft5,"RydenX14-02",user1,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("3.71", "4333,47USD", R.drawable.nft6,"RydenZW2-14",user,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("4.02", "4695,56USD", R.drawable.rebels,"Ryden71-03",user3,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("3.14", "3667,68USD", R.drawable.ryden_g2,"RydenX1-22",user1,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("3.18", "3714,40USD", R.drawable.ryden,"RydenX2-28",user,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("4.51", "5267,91USD", R.drawable.overstudy_g1,"OverStudy-5v2",user1,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("4.55", "5314,63USD", R.drawable.arthilarius_g1,"ArtHilarious-g2",user2,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("7.92", "9250,96USD", R.drawable.nft4,"KarthUXW-g14",user,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("12.01", "14028,28USD", R.drawable.a7d84f35d60e345fd2f70d053a6d01b7,"ZED-g14",user2,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                };

        */

       /* for(NFTModel nftModel : nftModels)
        {
            boolean result = db.insertNFT(nftModel);
            Log.i("inserted",String.valueOf(result));
        }

        */
        User[] users = new User[]
                {
                        user1,
                        user2,
                        user3
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