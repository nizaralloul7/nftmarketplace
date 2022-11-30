package com.example.nftmarketplace;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nftmarketplace.models.Bid;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class DetailsActivity extends AppCompatActivity
{
    private ImageView nftImage;
    private TextView nftNameText, nftPriceETH, nftPriceUSD, descriptionNft;
    private AlertDialog.Builder dialogBuidler;
    private AlertDialog alertDialog;
    private EditText amountPopup;
    private Button cancelButtonPopUp, placeButtonPopup, placeBidButton;
    DBHelper db;
    BottomNavigationView bottomNavigationView;

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
        placeBidButton = findViewById(R.id.placeBid);
        descriptionNft = findViewById(R.id.descriptionNft);
        bottomNavigationView = findViewById(R.id.bottonnav);
        bottomNavigationView.setSelectedItemId(R.id.home);

        if(intent.getExtras() != null)
        {
            nftImage.setImageResource(intent.getIntExtra("nftImage",R.drawable.khalifa_g2));
            nftNameText.setText(intent.getStringExtra("nftNameText"));
            nftPriceETH.setText(intent.getStringExtra("nftPriceETH"));
            nftPriceUSD.setText(intent.getStringExtra("nftPriceUSD"));
           // descriptionNft.setText(intent.getStringExtra("nftDescription"));
        }

        placeBidButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                DetailsActivity.this.createPopupView();
            }
        });

    }

    public void createPopupView()
    {
        dialogBuidler = new AlertDialog.Builder(this);
        final View bidPopup = getLayoutInflater().inflate(R.layout.popup_window, null);
        amountPopup = bidPopup.findViewById(R.id.amount);
        cancelButtonPopUp = bidPopup.findViewById(R.id.cancel);
        placeButtonPopup = bidPopup.findViewById(R.id.place);

        SharedPreferences sharedpreferences = getSharedPreferences(LoginActivity.MyPREFERENCES, Context.MODE_PRIVATE);

        db = new DBHelper(DetailsActivity.this);

        dialogBuidler.setView(bidPopup);
        alertDialog = dialogBuidler.create();
        alertDialog.show();

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()
        {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item)
            {
                switch (item.getItemId())
                {
                    case R.id.logout :
                        finish();
                        startActivity(new Intent(DetailsActivity.this, LoginActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.home :
                        startActivity(new Intent(DetailsActivity.this, MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.bids :
                        startActivity(new Intent(DetailsActivity.this, BidsActivity.class));
                        overridePendingTransition(0,0);
                        return true;
                    case R.id.addnft:
                        return true;
                    case R.id.profile :
                        Intent i = new Intent(DetailsActivity.this, Profile.class);
                        i.putExtra("username",sharedpreferences.getString("userName","error") );
                        startActivity(i);
                        overridePendingTransition(0,0);
                        return true;
                }

                return false;
            }
        });

        cancelButtonPopUp.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                alertDialog.dismiss();
            }
        });

        placeButtonPopup.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int nftId = db.getNFTIdByName(DetailsActivity.this.getIntent().getStringExtra("nftNameText"));
                int currentNftOwner = db.getIdOwnerByNftId(nftId);
                String ownerUsername = sharedpreferences.getString("userName", "error");
                int userId = db.getUserIdByUsername(ownerUsername);
                double amount = Double.parseDouble(amountPopup.getText().toString());
                Bid bid = new Bid(userId,amount,nftId, currentNftOwner);
                boolean isInserted = db.insertBid(bid);

                if(isInserted)
                {
                    alertDialog.dismiss();
                    Toast.makeText(DetailsActivity.this,"Bid placed succesfully", Toast.LENGTH_LONG).show();
                }
                else
                {
                    alertDialog.dismiss();
                    Toast.makeText(DetailsActivity.this,"Bid wasnt placed", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}