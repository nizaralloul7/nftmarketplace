package com.example.nftmarketplace.models;

public class Bid
{
    private User user;
    private double amountETH;
    private NFTModel nftModel;

    public Bid(User user, double amountETH, NFTModel nftModel)
    {
        this.user = user;
        this.amountETH = amountETH;
        this.nftModel = nftModel;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }

    public double getAmountETH()
    {
        return amountETH;
    }

    public void setAmountETH(double amountETH)
    {
        this.amountETH = amountETH;
    }

    public NFTModel getNftModel()
    {
        return nftModel;
    }

    public void setNftModel(NFTModel nftModel)
    {
        this.nftModel = nftModel;
    }
}
