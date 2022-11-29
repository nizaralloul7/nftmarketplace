package com.example.nftmarketplace.models;

public class Bid
{
    private int userId;
    private double amountETH;
    private int nftModelId;
    private int currentNftOwner;

    public Bid(int userId, double amountETH, int nftModelId, int currentNftOwner)
    {
        this.userId = userId;
        this.amountETH = amountETH;
        this.nftModelId = nftModelId;
        this.currentNftOwner = currentNftOwner;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getNftModelId() {
        return nftModelId;
    }

    public void setNftModelId(int nftModelId) {
        this.nftModelId = nftModelId;
    }

    public int getCurrentNftOwner() {
        return currentNftOwner;
    }

    public void setCurrentNftOwner(int currentNftOwner) {
        this.currentNftOwner = currentNftOwner;
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
