package com.example.nftmarketplace.adapters;

import android.annotation.SuppressLint;
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

import com.example.nftmarketplace.Profile;
import com.example.nftmarketplace.R;
import com.example.nftmarketplace.models.User;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.ViewHolder>
{
    private User[] users;
    private Context context;

    public UsersAdapter(User[] users, Context context)
    {
        this.users = users;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem = layoutInflater.inflate(R.layout.users_layout, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position)
    {
        final User myListData = users[position];
        holder.userIcon.setImageResource(users[position].getUserLogo());
        holder.name.setText("@" + users[position].getUserName());

        holder.relativeLayout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent i = new Intent(context, Profile.class);
                i.putExtra("username", users[position].getUserName());

                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return users.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView userIcon;
        public TextView name;
        public RelativeLayout relativeLayout;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            userIcon = itemView.findViewById(R.id.userIcon);
            name = itemView.findViewById(R.id.name);

            relativeLayout = itemView.findViewById(R.id.relativeUser);
        }
    }

}
