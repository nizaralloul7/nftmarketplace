package com.example.nftmarketplace;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nftmarketplace.models.Bid;

public class DetailsActivity extends AppCompatActivity
{
    private ImageView nftImage;
    private TextView nftNameText, nftPriceETH, nftPriceUSD;
    private AlertDialog.Builder dialogBuidler;
    private AlertDialog alertDialog;
    private EditText amountPopup;
    private Button cancelButtonPopUp, placeButtonPopup, placeBidButton;
    DBHelper db;

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

        if(intent.getExtras() != null)
        {
            nftImage.setImageResource(intent.getIntExtra("nftImage",R.drawable.khalifa_g2));
            nftNameText.setText(intent.getStringExtra("nftNameText"));
            nftPriceETH.setText(intent.getStringExtra("nftPriceETH"));
            nftPriceUSD.setText(intent.getStringExtra("nftPriceUSD"));
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

        db = new DBHelper(DetailsActivity.this);

        dialogBuidler.setView(bidPopup);
        alertDialog = dialogBuidler.create();
        alertDialog.show();

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
                Log.i("NFTNAME : ", DetailsActivity.this.getIntent().getStringExtra("nftNameText") );
                Log.i("NFTID : " , String.valueOf(nftId));
                int userId = db.getUserIdByUsername("test");
                Log.i("USERID : ", String.valueOf(userId));
                double amount = Double.parseDouble(amountPopup.getText().toString());
                Log.i("AMOUNT : ", String.valueOf(amount));
                Bid bid = new Bid(userId,amount,nftId);
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