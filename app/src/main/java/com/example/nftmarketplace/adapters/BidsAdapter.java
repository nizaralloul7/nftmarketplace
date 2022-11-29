package com.example.nftmarketplace.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nftmarketplace.BidsActivity;
import com.example.nftmarketplace.DBHelper;
import com.example.nftmarketplace.LoginActivity;
import com.example.nftmarketplace.R;
import com.example.nftmarketplace.models.Bid;
import com.example.nftmarketplace.models.NFTModel;
import com.google.android.gms.common.util.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class BidsAdapter extends RecyclerView.Adapter<BidsAdapter.ViewHolder>
{

    private Bid[] bidList;
    private Context context;
    private DBHelper dbHelper;
    private String currentUsername;

    public BidsAdapter(Bid[] bidList, Context context, String currentUsername)
    {
        this.bidList = bidList;
        this.context = context;
        this.currentUsername = currentUsername;
    }

    @NonNull
    @Override
    public BidsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.notification_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BidsAdapter.ViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        dbHelper = new DBHelper(context);
        if(position != -1)
        {
            int nftImage = dbHelper.getNftPicByNftId(bidList[position].getNftModel());
            String nftName = dbHelper.getNftNameById(bidList[position].getNftModel());
            String username = dbHelper.getUsernameById(bidList[position].getUser());
            holder.nftImage.setImageResource(nftImage);
            Log.i("nftImage", String.valueOf(nftImage));
            holder.bidAmount.setText(bidList[position].getAmountETH() + "ETH");
            holder.userNameText.setText("@"+username +" placed a bid on "+nftName);

            holder.refuse.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    boolean result = dbHelper.deleteBidByArgs(bidList[position].getAmountETH(), bidList[position].getUser(), bidList[position].getNftModel());
                    if(result)
                    {
                        bidList = ArrayUtils.removeAll(bidList, bidList[position]);
                        if(bidList.length == 0)
                            notifyDataSetChanged();
                        else
                            notifyItemRemoved(position);
                        Toast.makeText(context,"Deleted successful", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(context,"Deleting bid wasnt successful", Toast.LENGTH_LONG).show();
                }
            });

            holder.accept.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    double newBalanceCurrent = dbHelper.getUser(currentUsername).getBalance() + bidList[position].getAmountETH();
                    double newBalanceOtherUser = dbHelper.getUser(username).getBalance() - bidList[position].getAmountETH();
                    boolean resultUpdateUserC = dbHelper.updateUserBalanceByUsername(newBalanceCurrent, currentUsername);
                    boolean resultUpdateNftOwner = dbHelper.updateNftOwnerByNftName(bidList[position].getAmountETH(), bidList[position].getUser(),nftName);
                    boolean resultUpdateOtherUserBalance = dbHelper.updateUserBalanceByUsername(newBalanceOtherUser, username);


                    boolean result1 = dbHelper.deleteBidByArgs(bidList[position].getAmountETH(), bidList[position].getUser(), bidList[position].getNftModel());
                    if(resultUpdateUserC)
                    {

                        bidList = ArrayUtils.removeAll(bidList, bidList[position]);
                        if(bidList.length == 0)
                            notifyDataSetChanged();
                        else
                            notifyItemRemoved(position);
                        Toast.makeText(context,"User balance updated successful", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(context,"user balance failed to update", Toast.LENGTH_LONG).show();
                }
            });
        }

    }

    @Override
    public int getItemCount()
    {
        return bidList.length;
    }

    public class ViewHolder extends RecyclerView.ViewHolder
    {
        ImageView nftImage;
        TextView userNameText, bidAmount;
        TextView accept, refuse;
        RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            nftImage = itemView.findViewById(R.id.nftImage);
            userNameText = itemView.findViewById(R.id.userNameText);
            bidAmount = itemView.findViewById(R.id.bidAmount);

            accept = itemView.findViewById(R.id.accept);
            refuse = itemView.findViewById(R.id.refuse);

            relativeLayout = itemView.findViewById(R.id.relativeLayoutNotif);
        }
    }

}
