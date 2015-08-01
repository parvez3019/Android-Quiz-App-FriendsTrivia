package com.example.parvez.friendstrivia;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DataSource {

    private SQLiteDatabase database;
    private  MyDatabaseHelper dbHelper;
    private String[] allColumns={"_id","question","option_1","option_2","option_3","option_4","answer"};


    public DataSource(Context context)
    {
        dbHelper=new MyDatabaseHelper(context);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close() {
        dbHelper.close();
    }


    public List<Questions> getAllQuestions() {
        List<Questions> questionsList = new ArrayList<Questions>();


        Cursor cursor = database.query("questions",
                allColumns,null,null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Questions questions = cursorToQuestion(cursor);
            questionsList.add(questions);
            cursor.moveToNext();

        }
        // make sure to close the cursor
        cursor.close();
        return questionsList;
    }

   private Questions cursorToQuestion(Cursor cursor) {
        Questions questions = new Questions();
        questions.setQues(cursor.getString(1));
        questions.setOp1(cursor.getString(2));
        questions.setOp2(cursor.getString(3));
        questions.setOp3(cursor.getString(4));
        questions.setOp4(cursor.getString(5));
        questions.setAns(cursor.getString(6));
        //Log.d("1st"+cursor.getString(0),"2nd"+cursor.getString(2));
        return questions;
    }
}


