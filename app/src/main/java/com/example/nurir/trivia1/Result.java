package com.example.nurir.trivia1;

public class Result {
  private String date, userID, ID;
  private int score;

    public Result() {
    }

    public Result(String ID, String date, String userID, int score) {
        this.ID = ID;
        this.date = date;
        this.userID = userID;
        this.score = score;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }
}
