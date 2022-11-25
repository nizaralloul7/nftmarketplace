package com.example.nftmarketplace;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.nftmarketplace.adapters.NFTAdapter;
import com.example.nftmarketplace.models.NFTModel;
import com.example.nftmarketplace.models.User;

public class MainActivity extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        User user1 = new User("Nizar", "Alloul", "Shigur","motdepasse123","email",14.55,R.drawable.ryden);
        User user2 = new User("Othmane", "Lz", "TfBlade","motdepasse123","email",14.55,R.drawable.overstudy_g1);

        NFTModel[] nftModels = new NFTModel[]
                {
                        new NFTModel("3.14ETH", "2477USD", R.drawable.ryden_g2,"RydenX1-22",user1,"This Nft is ..."),
                        new NFTModel("3.18ETH", "2500USD", R.drawable.ryden,"RydenX2-28",user2,"This Nft is ..."),
                        new NFTModel("4.51ETH", "3100USD", R.drawable.overstudy_g1,"OverStudy-5v2",user1,"This Nft is ..."),
                        new NFTModel("4.55ETH", "3254USD", R.drawable.arthilarius_g1,"ArtHilarious-g2",user2,"This Nft is ..."),
                };

        RecyclerView recyclerViewNfts = findViewById(R.id.recyclerViewNfts);
        NFTAdapter nftAdapter = new NFTAdapter(nftModels,this);
        recyclerViewNfts.setHasFixedSize(true);
        recyclerViewNfts.setLayoutManager(new GridLayoutManager(this,2));
        recyclerViewNfts.setAdapter(nftAdapter);

    }
}