package com.example.nftmarketplace.adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nftmarketplace.DetailsActivity;
import com.example.nftmarketplace.R;
import com.example.nftmarketplace.models.NFTModel;

import java.util.List;

public class NFTAdapter extends RecyclerView.Adapter<NFTAdapter.ViewHolder>
{

    private NFTModel[] nftModelList;
    private Context context;

    public NFTAdapter(NFTModel[] nftModelList, Context context)
    {
        this.nftModelList = nftModelList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.nft_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position)
    {

        final NFTModel myListData = nftModelList[position];
        holder.nftImage.setImageResource(nftModelList[position].getNftPic());
        holder.userIcon.setImageResource(nftModelList[position].getOwner().getUserLogo());
        holder.nftNameText.setText(nftModelList[position].getNftName());
        holder.nftPriceETH.setText(nftModelList[position].getPriceETH());
        holder.nftPriceUSD.setText(nftModelList[position].getPriceUSD());
        holder.ownerName.setText(nftModelList[position].getOwner().getUserName());
        holder.ownerNameAt.setText('@' + nftModelList[position].getOwner().getUserName());

        holder.nftImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                int pos = holder.getAdapterPosition();
                Intent i = new Intent(context, DetailsActivity.class);
                i.putExtra("nftImage",nftModelList[pos].getNftPic());
                i.putExtra("nftNameText",nftModelList[pos].getNftName());
                i.putExtra("nftPriceETH",nftModelList[pos].getPriceETH());
                i.putExtra("nftPriceUSD",nftModelList[pos].getPriceUSD());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return nftModelList.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView nftImage, userIcon;
        public TextView nftNameText, nftPriceETH, nftPriceUSD, ownerName, ownerNameAt;
        public RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            nftImage = itemView.findViewById(R.id.nftImage);
            userIcon = itemView.findViewById(R.id.userIcon);

            nftNameText = itemView.findViewById(R.id.nftNameText);
            nftPriceETH = itemView.findViewById(R.id.nftPriceETH);
            nftPriceUSD = itemView.findViewById(R.id.nftPriceUSD);
            ownerName = itemView.findViewById(R.id.ownerName);
            ownerNameAt = itemView.findViewById(R.id.ownerNameAt);

            relativeLayout = itemView.findViewById(R.id.relativeLayout);

        }
    }
}
