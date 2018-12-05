package com.example.nurir.trivia1;

public class Users {
    private int ID;
    private String USERNAME, PASSWORD;
   // private String EMAIL;

    public Users()
    {
        ID = 0;
        USERNAME = "";
       // EMAIL = "";
        PASSWORD = "";
    }
    public Users(String username, String password)
    {
        this.USERNAME = username;
        this.PASSWORD = password;
    }
    public int getID() {
        return ID;
    }
    public String getUSERNAME() {
        return USERNAME;
    }
    /*public String getEMAIL() {
        return EMAIL;
    }*/
    public String getPASSWORD() {
        return PASSWORD;
    }
    public void setID(int ID) {
        this.ID = ID;
    }
    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }
   /* public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }*/
    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }
}
