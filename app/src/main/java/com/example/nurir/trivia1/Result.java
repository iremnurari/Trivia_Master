package com.example.nurir.trivia1;

public class Result {
    private int ID, Score;
    private String Username, Date;

    public Result(){
        ID = 0;
        Username = "";
        Score = 0;
        Date = "";
    }

    public Result(String date, String  Username, int Score){
        this.Date = date;
        this.Username = Username;
        this.Score = Score;
    }

    public int getID() {
        return ID;
    }

    public int getScore() {
        return Score;
    }

    public String  getUsername() {
        return Username;
    }

    public String getDate() {
        return Date;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setScore(int score) {
        Score = score;
    }

    public void setDate(String date) {
        Date = date;
    }
}
