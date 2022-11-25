package com.example.nftmarketplace.models;


import java.util.List;

public class User
{
    private String firstName;
    private String lastName;
    private String userName;
    private String password;
    private String email;
    private double balance;
    private Integer userLogo;
    private List<NFTModel> ownedNfts;

    public User(String firstName, String lastName, String userName, String password, String email, double balance, Integer userLogo)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.balance = balance;
        this.userLogo = userLogo;
    }

    public List<NFTModel> getOwnedNfts() {
        return ownedNfts;
    }

    public void setOwnedNfts(List<NFTModel> ownedNfts) {
        this.ownedNfts = ownedNfts;
    }

    public Integer getUserLogo() {
        return userLogo;
    }

    public void setUserLogo(Integer userLogo) {
        this.userLogo = userLogo;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getLastName()
    {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
