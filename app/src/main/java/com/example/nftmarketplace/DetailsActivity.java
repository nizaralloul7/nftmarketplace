package com.example.nftmarketplace;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailsActivity extends AppCompatActivity
{
    ImageView nftImage;
    TextView nftNameText, nftPriceETH, nftPriceUSD;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        Intent intent = getIntent();

        nftImage = findViewById(R.id.nftImage);
        nftNameText = findViewById(R.id.nftNameText);
        nftPriceETH = findViewById(R.id.nftPriceETH);
        nftPriceUSD = findViewById(R.id.nftPriceUSD);

        if(intent.getExtras() != null)
        {
            nftImage.setImageResource(intent.getIntExtra("nftImage",R.drawable.khalifa_g2));
            nftNameText.setText(intent.getStringExtra("nftNameText"));
            nftPriceETH.setText(intent.getStringExtra("nftPriceETH"));
            nftPriceUSD.setText(intent.getStringExtra("nftPriceUSD"));
        }

    }
}