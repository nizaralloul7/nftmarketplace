package com.example.nftmarketplace;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

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
        db.execSQL("CREATE TABLE Bid(Id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, currentNftOwner INTEGER NOT NULL, ownerId INTEGER NOT NULL, nftId INTEGER NOT NULL, " +
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


        long userId = bid.getUser();

        long nftId = bid.getNftModel();
        contentValues.put("ownerId",userId);
        contentValues.put("amount",bid.getAmountETH());
        contentValues.put("nftId",nftId);
        contentValues.put("currentNftOwner", bid.getCurrentNftOwner());

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
        int userId = this.getUserIdByUsername(username);
        SQLiteDatabase db = this.getReadableDatabase();
        List<NFTModel> nfts = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM NFT WHERE ownerId = ?", new String[]{String.valueOf(userId)});

        User usr = this.getUser(username);

        if(cursor.moveToFirst())
        {
            do
            {
                nfts.add(
                        new NFTModel(cursor.getString(cursor.getColumnIndex("PriceETH")),
                                cursor.getString(cursor.getColumnIndex("PriceUSD")),
                                Integer.parseInt(cursor.getString(cursor.getColumnIndex("NftPicture"))),
                                cursor.getString(cursor.getColumnIndex("NftName")),
                                usr,
                                cursor.getString(cursor.getColumnIndex("Description"))
                                )
                );
            }
            while (cursor.moveToNext());
        }

        return nfts;
    }

    @SuppressLint("Range")
    public int getNFTIdByName(String nftName)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Id FROM NFT WHERE NftName = ?", new String[]{nftName});

        if(cursor.moveToFirst())
        {
            return Integer.parseInt(cursor.getString(cursor.getColumnIndex("Id")));
        }
        else
            return 0;

    }

    @SuppressLint("Range")
    public int getUserIdByUsername(String username)
    {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT Id FROM User WHERE Username = ?", new String[]{username});

        if(cursor.moveToFirst())
        {
            return Integer.parseInt(cursor.getString(cursor.getColumnIndex("Id")));
        }
        else
            return 0;
    }

    public boolean insertNFT(NFTModel nft)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("PriceETH", nft.getPriceETH());
        contentValues.put("PriceUSD", nft.getPriceUSD());
        contentValues.put("NftName", nft.getNftName());
        contentValues.put("Description", nft.getDescription());
        contentValues.put("NftPicture", nft.getNftPic());
        contentValues.put("ownerId",this.getUserIdByUsername(nft.getOwner().getUserName()));

        long result = db.insert("NFT", null, contentValues);

        if(result == -1)
        {
            return false;
        }
        else
            return true;
    }

    @SuppressLint("Range")
    public int getNftPicByNftId(int nftId)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT NftPicture FROM NFT WHERE Id = ?", new String[]{String.valueOf(nftId)});

        if(cursor.moveToFirst())
        {
            return Integer.parseInt(cursor.getString(cursor.getColumnIndex("NftPicture"))) ;
        }
        else
            return 0;
    }

    @SuppressLint("Range")
    public String getNftNameById(int nftId)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT NftName FROM NFT WHERE Id = ?", new String[]{String.valueOf(nftId)});

        if(cursor.moveToFirst())
        {
            return cursor.getString(cursor.getColumnIndex("NftName")) ;
        }
        else
            return " ";
    }

    @SuppressLint("Range")
    public String getUsernameById(int userId)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT Username FROM User WHERE Id = ?", new String[]{String.valueOf(userId)});

        if(cursor.moveToFirst())
        {
            return cursor.getString(cursor.getColumnIndex("Username"));
        }
        else
            return " ";
    }

    public boolean deleteBidByArgs(double amount, int userId, int nftId)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        long result = db.delete("Bid","amount = ? AND ownerId = ? AND nftId = ?",new String[]{String.valueOf(amount), String.valueOf(userId), String.valueOf(nftId)});

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateUserBalanceByUsername(double newAmount, String username)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("Balance", newAmount);

        long result = db.update("User", contentValues, "Username = ?", new String[]{username});

        if(result == -1)
            return false;
        else
            return true;
    }

    public boolean updateNftOwnerByNftName(double newPrice, int newOwner, String nftName)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put("ownerId", newOwner);
        contentValues.put("PriceETH", newPrice);

        long result = db.update("NFT", contentValues, "nftName = ?", new String[]{nftName});

        if(result == -1)
            return false;
        else
            return true;
    }

    @SuppressLint("Range")
    public Bid[] getBidsByUser(String username)
    {
        int userId = this.getUserIdByUsername(username);
        SQLiteDatabase db = this.getReadableDatabase();
        List<Bid> bids = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM Bid WHERE currentNftOwner = ?", new String[]{String.valueOf(userId)});

        if(cursor.moveToFirst())
        {

            do
            {
                bids.add(
                        new Bid(
                                Integer.parseInt(cursor.getString(cursor.getColumnIndex("ownerId"))),
                                Double.parseDouble(cursor.getString(cursor.getColumnIndex("amount"))),
                                Integer.parseInt(cursor.getString(cursor.getColumnIndex("nftId"))),
                                this.getIdOwnerByNftId(Integer.parseInt(cursor.getString(cursor.getColumnIndex("nftId"))))
                        )
                );
            }
            while (cursor.moveToNext());
        }
        Bid[] returnedBids = bids.toArray(new Bid[bids.size()]);

        return returnedBids;
    }

    @SuppressLint("Range")
    public List<NFTModel> getNfts()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        List<NFTModel> nfts = new ArrayList<>();

        Cursor cursor = db.rawQuery("SELECT * FROM NFT", null);
        if(cursor.moveToFirst())
        {
            do
            {
                nfts.add(
                        new NFTModel(cursor.getString(cursor.getColumnIndex("PriceETH")),
                                cursor.getString(cursor.getColumnIndex("PriceUSD")),
                                Integer.parseInt(cursor.getString(cursor.getColumnIndex("NftPicture"))),
                                cursor.getString(cursor.getColumnIndex("NftName")),
                                getUser(getUsernameById(Integer.parseInt(cursor.getString(cursor.getColumnIndex("ownerId"))))),
                                cursor.getString(cursor.getColumnIndex("Description"))
                                )
                );

            }
            while (cursor.moveToNext());
        }

        return nfts;
    }

    @SuppressLint("Range")
    public int getIdOwnerByNftId(int nftId)
    {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT ownerId FROM NFT WHERE Id = ?", new String[]{String.valueOf(nftId)});

        if(cursor.moveToFirst())
        {
            return Integer.parseInt(cursor.getString(cursor.getColumnIndex("ownerId")));
        }
        else
            return 0;
    }


}
