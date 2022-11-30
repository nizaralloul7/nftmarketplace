package com.example.nftmarketplace.models;

public class NFTModel
{
    private String priceETH;
    private String priceUSD;
    private Integer nftPic;
    private String nftName;
    private User owner;
    private String description;

    public NFTModel(String priceETH, String priceUSD, Integer nftPic, String nftName, User owner, String description)
    {
        this.priceETH = priceETH;
        this.priceUSD = priceUSD;
        this.nftPic = nftPic;
        this.nftName = nftName;
        this.owner = owner;
        this.description = description;
    }

    public String getPriceETH() {
        return priceETH;
    }

    public void setPriceETH(String priceETH) {
        this.priceETH = priceETH;
    }

    public String getPriceUSD() {
        return priceUSD;
    }

    public void setPriceUSD(String priceUSD) {
        this.priceUSD = priceUSD;
    }

    public Integer getNftPic() {
        return nftPic;
    }

    public void setNftPic(Integer nftPic) {
        this.nftPic = nftPic;
    }

    public String getNftName() {
        return nftName;
    }

    public void setNftName(String nftName) {
        this.nftName = nftName;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
