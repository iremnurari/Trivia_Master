package com.example.nurir.trivia1;
import java.util.ArrayList;
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
    private static final String Q_Ans= "answer";
    private static final String Q_optA = "opta";
    private static final String Q_optB = "optb";
    private static final String Q_optC = "optc";
    private static final String T_User = "SavedUsers";
    private static final String U_ID = "ID";
    private static final String U_uname = "USERNAME";
    private static final String U_password = "PASSWORD";
    private static final String T_Result = "Results";
    private static final String R_ID = "ID";
    private static final String R_UserName = "User";
    private static final String R_score = "Score";
    private static final String R_Date = "Date";
    public DatabaseHelper(Context context) {
        super(context, DBName, null, DBVersion);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_QuesTable = "CREATE TABLE IF NOT EXISTS " +T_Quest+ " ( "
                +Q_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +Q_Quest+
                " TEXT, " + Q_optA +" TEXT, " +Q_optB+ " TEXT, "
                +Q_optC+ " TEXT, "+ Q_Ans + " TEXT);";
        String SQL_UserTable = "CREATE TABLE IF NOT EXISTS " +T_User+ " ( "
                +U_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +U_uname+
                " TEXT, " +U_password+ " TEXT);";
        //+U_email+ " TEXT, "
        String SQL_ResultTable = "CREATE TABLE IF NOT EXISTS " +T_Result+ " ("
                +R_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT, " +R_Date+ " TEXT, " + R_UserName +
                " TEXT, " +R_score+ " INTEGER);";
        db.execSQL(SQL_QuesTable);
        db.execSQL(SQL_UserTable);
        db.execSQL(SQL_ResultTable);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
        db.execSQL("DROP TABLE IF EXISTS " + T_Quest);
        db.execSQL("DROP TABLE IF EXISTS " +T_User);
        db.execSQL("DROP TABLE IF EXISTS " +T_Result);
        onCreate(db);
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
    {//make an admin screen to add questions
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

    // Adding new question
    public void addQuestion(Question quest) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Q_Quest, quest.getQUESTION());
        values.put(Q_optA, quest.getOPTA());
        values.put(Q_optB, quest.getOPTB());
        values.put(Q_optC, quest.getOPTC());
        values.put(Q_Ans, quest.getANSWER());
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
                quest.setOPTA(cursor.getString(2));
                quest.setOPTB(cursor.getString(3));
                quest.setOPTC(cursor.getString(4));
                quest.setANSWER(cursor.getString(5));
                quesList.add(quest);
            } while (cursor.moveToNext());
        }
        // return quest list
        return quesList;
    }
    public Question findQuestion (int id){
        SQLiteDatabase dbase = this.getReadableDatabase();
        Question question = null;
        String SQL = "Select * From " +T_Quest+ " Where " +Q_ID+ "=" +id+ ";";
        Cursor cursor = dbase.rawQuery(SQL, null);
        if(cursor != null){
            cursor.moveToFirst();
        }
        if(cursor != null && cursor.getCount() > 0){
            question = new Question(cursor.getInt(0), cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));
        }
        return question;
    }
    public void editQuestion(Question q){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        String where = Q_ID+ "=?";
        String [] whereArgs = new String[] {String.valueOf(q.getID())};
        contentValues.put(Q_Quest, q.getQUESTION());
        contentValues.put(Q_optA, q.getOPTA());
        contentValues.put(Q_optB, q.getOPTB());
        contentValues.put(Q_optC, q.getOPTC());
        contentValues.put(Q_Ans, q.getANSWER());
        db.update(T_Quest, contentValues, where, whereArgs);
        db.close();
    }
    public void deleteQuestion(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String where = Q_ID+ " = "+id;
        db.delete(T_Quest, where,null);
    }
    public void saveResult(Result result){
        SQLiteDatabase dbase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(R_score, result.getScore());
        contentValues.put(R_UserName, result.getUsername());
        contentValues.put(R_Date, result.getDate());
        dbase.insert(T_Result, null,contentValues);
        dbase.close();
    }
    public ArrayList<Result> getAllresults(String username){
        ArrayList<Result> resList = new ArrayList<Result>();
        String selectQuery = "SELECT  * FROM " +T_Result+ " WHERE " + R_UserName + " = '" +username+ "';";
        SQLiteDatabase dbase=this.getReadableDatabase();
        Cursor cursor = dbase.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                Result result = new Result();
                result.setID(cursor.getInt(0));
                result.setDate(cursor.getString(1));
                result.setUsername(cursor.getString(2));
                result.setScore(cursor.getInt(3));
                resList.add(result);
            } while (cursor.moveToNext());
        }
        // return quest list
        return resList;
    }
    /*public int rowcount()
    {
        int row=0;
        String selectQuery = "SELECT  * FROM " + T_Quest;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        row=cursor.getCount();
        return row;
    }*/
}
