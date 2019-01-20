package com.example.nurir.trivia1;

public class Users {
    private String USERNAME, EMAIL;


    public Users()
    {
    }
    public Users(String username, String email)
    {
        this.USERNAME = username;
        this.EMAIL = email;
    }
    public String getUSERNAME() {
        return USERNAME;
    }
    public String getEMAIL() {
        return EMAIL;
    }
    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }
    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

}
