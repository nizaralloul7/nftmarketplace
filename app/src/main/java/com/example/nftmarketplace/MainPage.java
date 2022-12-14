package com.example.nftmarketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.nftmarketplace.models.NFTModel;
import com.example.nftmarketplace.models.User;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPage extends AppCompatActivity
{
    Button btnRegister, btnLogIn;
    DBHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        db = new DBHelper(this);

        btnRegister = findViewById(R.id.registerButton);
        btnLogIn = findViewById(R.id.signInButton);


        User user1 = new User("Nizar", "Alloul", "Shigur","motdepasse123","nizaralloul7@gmail.com",120.04,R.drawable.profile1);
        User user2 = new User("Othmane", "Lz", "TfBlade","motdepasse123","othmane@gmaile.com",111.00,R.drawable.profile2);
        User user3 = new User("Mounia", "Ech", "MEchel","motdepasse123","mounia@gmail.com",314.54,R.drawable.profile3);
        User user4 = new User("Basma", "Haimeur", "Smilie","motdepasse123","basma.haimeur@gmail.com",102.01,R.drawable.anothermonkey);
        User user5 = new User("Yahya", "Bjrh", "Ybjrh","motdepasse123","Yahya.boujrah@gmail.com",87.14,R.drawable.profile4);


        //User usr = db.getUser("test");

        NFTModel[] nftModels = new NFTModel[]
                {
                        new NFTModel("3.18", "3714,40USD", R.drawable.nft1,"RydenHat-15",user1,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("4.85", "5665,04USD", R.drawable.nft2,"RydenSam-05",user2,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("8.01", "9356,08USD", R.drawable.nft3,"JhinGlas-X",user3,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("2.54", "2966,85USD", R.drawable.nft5,"RydenX14-02",user1,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("3.71", "4333,47USD", R.drawable.nft6,"RydenZW2-14",user5,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("4.02", "4695,56USD", R.drawable.rebels,"Ryden71-03",user4,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("3.14", "3667,68USD", R.drawable.ryden_g2,"RydenX1-22",user1,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("9.92", "12623,96USD", R.drawable.hape2,"HapeColl-20",user3,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("8.64", "10994,66USD", R.drawable.hape3,"HaperCool-g1",user3,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("5.12", "6515,35USD", R.drawable.hape4,"Hapester-02",user4,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("11.02", "14023,28USD", R.drawable.hape5,"HapeB-x14",user3,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("3.18", "3714,40USD", R.drawable.ryden,"RydenX2-28",user2,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("4.51", "5267,91USD", R.drawable.overstudy_g1,"OverStudy-5v2",user1,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("4.55", "5314,63USD", R.drawable.arthilarius_g1,"ArtHilarious-g2",user2,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("7.92", "9250,96USD", R.drawable.nft4,"KarthUXW-g14",user3,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("12.01", "14028,28USD", R.drawable.a7d84f35d60e345fd2f70d053a6d01b7,"ZED-g14",user5,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),
                        new NFTModel("14.92", "18986,15USD", R.drawable.hape1,"HapePrime-12",user4,"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Mauris ut mauris a lorem pulvinar sollicitudin non nec neque. Nullam ultricies laoreet arcu quis consequat. Vestibulum ac est scelerisque, consequat lorem id, feugiat sem."),

                };

        db.insertUser(user1);
        db.insertUser(user2);
        db.insertUser(user3);
        db.insertUser(user4);
        db.insertUser(user5);

        for(NFTModel nftModel : nftModels)
        {
            boolean result = db.insertNFT(nftModel);
            Log.i("inserted",String.valueOf(result));
        }


        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainPage.this, Registration.class));
            }
        });

        btnLogIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                startActivity(new Intent(MainPage.this, LoginActivity.class));
            }
        });

    }
}