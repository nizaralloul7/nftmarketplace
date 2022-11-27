package com.example.nftmarketplace.models;

public class Bid
{
    private int userId;
    private double amountETH;
    private int nftModelId;

    public Bid(int userId, double amountETH, int nftModelId)
    {
        this.userId = userId;
        this.amountETH = amountETH;
        this.nftModelId = nftModelId;
    }

    public int getUser()
    {
        return userId;
    }

    public void setUser(int userId)
    {
        this.userId = userId;
    }

    public double getAmountETH()
    {
        return amountETH;
    }

    public void setAmountETH(double amountETH)
    {
        this.amountETH = amountETH;
    }

    public int getNftModel()
    {
        return nftModelId;
    }

    public void setNftModel(NFTModel nftModel)
    {
        this.nftModelId = nftModelId;
    }
}
