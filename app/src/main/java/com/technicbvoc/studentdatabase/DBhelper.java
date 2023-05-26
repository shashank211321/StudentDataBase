package com.technicbvoc.studentdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class DBhelper extends SQLiteOpenHelper {
    String tblname="studentdata";
    String colname="Name";
    String colmob="Mobile";
    String colloc="Location";
    String coldob="DOB";
    String coldepartment="Department";
    String qry="create table "+tblname+"("+colname+" text,"+colmob+" text,"
            +colloc+" text,"+coldob+" text,"+coldepartment+" text)";

    String res="";
    SQLiteDatabase sq;

    public DBhelper(Context context) {
        super(context, "studentd.db",null,1);

        sq=getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void savedata(String stname, String stmob, String stloc, String stdob, String stdepartment) {
        ContentValues v=new ContentValues();
        v.put(colname,stname);
        v.put(colmob,stmob);
        v.put(colloc,stloc);
        v.put(coldob,stdob);
        v.put(coldepartment,stdepartment);
        sq.insert(tblname,null,v);
    }


    public String getda(String stdepartment) {
        Cursor c;
        c= sq.query(tblname,null,coldepartment+"=?",new String[]{stdepartment},null,null,null);
        c.moveToFirst();
        if(c.getCount()<1)
        {
            return "no data";
        }
        do {
            res +="\n"+ c.getString(c.getColumnIndex(colname)) + "\n" +
                    c.getString(c.getColumnIndex(colmob)) + "\n" +
                    c.getString(c.getColumnIndex(colloc)) + "\n" +
                    c.getString(c.getColumnIndex(coldob))+"\n";
        }while (c.moveToNext());
        return res;
    }


}
