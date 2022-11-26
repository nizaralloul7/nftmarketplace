package com.example.nftmarketplace;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.nftmarketplace.models.Bid;
import com.example.nftmarketplace.models.NFTModel;
import com.example.nftmarketplace.models.User;

import java.util.ArrayList;
import java.util.List;

public class DBHelper extends SQLiteOpenHelper
{
    public DBHelper(Context context)
    {
        super(context, "NFTWarehouse.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE User(Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, Username TEXT NOT NULL, Email TEXT NOT NULL," +
                "Password TEXT NOT NULL, Balance REAL NOT NULL, UserLogo INTEGER)");
        db.execSQL("CREATE TABLE NFT(Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, PriceETH REAL NOT NULL, PriceUSD REAL NOT NULL," +
                "NftName TEXT NOT NULL, Description TEXT, NftPicture INTEGER, ownerId INTEGER NOT NULL ,FOREIGN KEY (ownerId) REFERENCES User (Id))");
        db.execSQL("CREATE TABLE Bid(Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, ownerId INTEGER NOT NULL, nftId INTEGER NOT NULL, " +
                "amount REAL NOT NULL, FOREIGN KEY (ownerId) REFERENCES User (Id), FOREIGN KEY (nftId) REFERENCES NFT (Id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1)
    {
        db.execSQL("DROP TABLE IF EXISTS User");
        db.execSQL("DROP TABLE IF EXISTS NFT");
    }

    public boolean insertUser(User user)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Username",user.getUserName());
        contentValues.put("Email",user.getEmail());
        contentValues.put("Password",user.getPassword());
        contentValues.put("Balance",user.getBalance());
        contentValues.put("UserLogo",user.getUserLogo());

        long result = db.insert("User", null, contentValues);

        if(result == -1)
        {
            return false;
        }
        else
            return true;
    }

    public boolean insertBid(Bid bid)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        Cursor cursor = db.rawQuery("SELECT Id FROM User WHERE Username = ?", new String[]{bid.getUser().getUserName()});
        @SuppressLint("Range")
        long userId = Long.parseLong(cursor.getString(cursor.getColumnIndex("Id")));
        Cursor cursor1 = db.rawQuery("SELECT Id FROM NFT WHERE nftName = ?", new String[]{bid.getNftModel().getNftName()});
        @SuppressLint("Range")
        long nftId = Long.parseLong(cursor1.getString(cursor.getColumnIndex("Id")));
        contentValues.put("ownerId",userId);
        contentValues.put("amount",bid.getAmountETH());
        contentValues.put("nftId",nftId);

        cursor.close();
        cursor1.close();

        long result = db.insert("Bid", null, contentValues);

        if(result == -1)
        {
            return false;
        }
        else
            return true;
    }

    public boolean loginAction(String username, String password)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE username = ? and password = ?", new String[]{username, password});

        if(cursor.getCount() > 0)
            return true;
        else
            return false;
    }
    @SuppressLint("Range")
    public User getUser(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM User WHERE username = ?", new String[]{username});

        if(cursor.moveToFirst())
        {
            User user = new User("usrname","lastname",cursor.getString(cursor.getColumnIndex("Username")),
                    cursor.getString(cursor.getColumnIndex("Password")),cursor.getString(cursor.getColumnIndex("Email")),
                    Double.parseDouble( cursor.getString(cursor.getColumnIndex("Balance"))),Integer.parseInt(cursor.getString(cursor.getColumnIndex("UserLogo")))
                    );
            return user;
        }
        else
            return null;
    }

    @SuppressLint("Range")
    public List<NFTModel> getNftByUser(String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor user = db.rawQuery("SELECT Id, Username FROM User WHERE Username = ?", new String[]{username});
        List<NFTModel> nftList = new ArrayList<>();

        String ownerId = user.getString(user.getColumnIndex("Id"));
        User usr = this.getUser(user.getString(user.getColumnIndex("Username")));

        Cursor nfts = db.rawQuery("SELECT * FROM NFT WHERE ownerId = ?", new String[]{ownerId});

        if(nfts.moveToFirst())
        {
            do
            {
                nftList.add(
                        new NFTModel(nfts.getString(nfts.getColumnIndex("PriceETH")),
                                     nfts.getString(nfts.getColumnIndex("PriceUSD")),
                                     Integer.parseInt(nfts.getString(nfts.getColumnIndex("NftPicture"))),
                                     nfts.getString(nfts.getColumnIndex("NftName")),
                                     usr,
                                     nfts.getString(nfts.getColumnIndex("Description"))
                                )
                );
            }
            while (nfts.isAfterLast());
        }

        return nftList;
    }


}
