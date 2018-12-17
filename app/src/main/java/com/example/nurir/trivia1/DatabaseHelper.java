package com.example.nurir.trivia1;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int DBVersion = 1;
    private static final String DBName = "triviaQuiz";
    private static final String T_Quest = "quest";
    private static final String Q_ID = "id";
    private static final String Q_Quest = "question";
    private static final String Q_Ans= "answer"; //correct option
    private static final String Q_optA = "opta"; //option a
    private static final String Q_optB = "optb"; //option b
    private static final String Q_optC = "optc"; //option c
    private static final String T_User = "SavedUsers";
    private static final String U_ID = "ID";
    private static final String U_uname = "USERNAME";
    //private static final String U_email = "EMAIL";
    private static final String U_password = "PASSWORD";
    public DatabaseHelper(Context context) {
        super(context, DBName, null, DBVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_QuesTable = "CREATE TABLE IF NOT EXISTS " +T_Quest+ " ( "
                +Q_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +Q_Quest+
                " TEXT, " + Q_Ans + " TEXT, "+ Q_optA +" TEXT, "
                +Q_optB+ " TEXT, " +Q_optC+ " TEXT);";
        String SQL_UserTable = "CREATE TABLE IF NOT EXISTS " +T_User+ " ( "
                +U_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +U_uname+
                " TEXT, " +U_password+ " TEXT);";
        //+U_email+ " TEXT, "
        db.execSQL(SQL_QuesTable);
        db.execSQL(SQL_UserTable);
    }
    public void addUser(Users user){
       SQLiteDatabase dbase = this.getWritableDatabase();
       ContentValues contentValues = new ContentValues();
       contentValues.put(U_uname, user.getUSERNAME());
       contentValues.put(U_password, user.getPASSWORD());
       dbase.insert(T_User, null,contentValues);
       dbase.close();
    }
    public Users searchUser(String u,String p){
        SQLiteDatabase dbase = this.getReadableDatabase();
        Users user = null;
        Cursor cursor = dbase.query(T_User,new String[]{U_ID, U_uname, U_password}, U_uname+ "=? and " + U_password + "=?",
                new String[]{u, p}, null, null, null, "1");
        if(cursor != null){
            cursor.moveToFirst();
        }
        if (cursor != null && cursor.getCount() > 0) {
            user = new Users(cursor.getString(1), cursor.getString(2));
        }
        return user;
    }

    public void addQuestions()
    {//make an admin screen to addquestions
        Question q1 = new Question("Which company is the largest manufacturer" +
                " of network equipment?","HP", "IBM", "CISCO", "CISCO");
        this.addQuestion(q1);
        Question q2 = new Question("Which of the following is NOT " +
                "an operating system?", "SuSe", "BIOS", "DOS", "BIOS");
        this.addQuestion(q2);
        Question q3 = new Question("Which of the following is the fastest" +
                " writable memory?","RAM", "FLASH","Register","Register");
        this.addQuestion(q3);
        Question q4 = new Question("Which of the following device" +
                " regulates internet traffic?",	"Router", "Bridge", "Hub","Router");
        this.addQuestion(q4);
        Question q5 = new Question("Which of the following is NOT an" +
                " interpreted language?","Ruby","Python","BASIC","BASIC");
        this.addQuestion(q5);
    }
    public void addNewQuestions(String question, String optionA, String optionB, String optionC, String answer){
        Question qn = new Question(question, optionA, optionB, optionC, answer);
        this.addQuestion(qn);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + T_Quest);
        db.execSQL("DROP TABLE IF EXISTS " +T_User);
        onCreate(db);
    }
    // Adding new question
    public void addQuestion(Question quest) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Q_Quest, quest.getQUESTION());
        values.put(Q_Ans, quest.getANSWER());
        values.put(Q_optA, quest.getOPTA());
        values.put(Q_optB, quest.getOPTB());
        values.put(Q_optC, quest.getOPTC());
        // Inserting Row
        db.insert(T_Quest, null, values);
        db.close();
    }
    public ArrayList<Question> getAllQuestions() {
        ArrayList<Question> quesList = new ArrayList<Question>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + T_Quest;
        SQLiteDatabase dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Question quest = new Question();
                quest.setID(cursor.getInt(0));
                quest.setQUESTION(cursor.getString(1));
                quest.setANSWER(cursor.getString(2));
                quest.setOPTA(cursor.getString(3));
                quest.setOPTB(cursor.getString(4));
                quest.setOPTC(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }
    public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + T_Quest;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }
}
