package com.example.asus_pc.locationalarm2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by asus-pc on 11/14/2017.
 */

public class dbhelper extends SQLiteOpenHelper{
    private static final String TABLE_NAME = "alarmloc" ;
      private static final  String dbname ="db1.db";
      private static final int dbv=4;
    private static   Context context1 =null;
      private static final  String stmt =

            "CREATE  TABLE " +TABLE_NAME+ " (" +
            "alarm_id integer," +
            "loc_id text," +
            "loc_name text," +
            "note text" +
            ")";

    public dbhelper(Context context) {
        super(context, dbname, null, dbv)        ;
             final Context context1 =context;

    }

    public void updateRow(Integer id, String loc_id, String loc_name, String note) {

        String stmt ="update alarmloc set loc_id = '" + loc_id +  "', loc_name = '" + loc_name + "', note = '" + note + "' where alarm_id = " + id.toString();

        SQLiteDatabase sql = this.getWritableDatabase();
        sql.execSQL(stmt);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(stmt);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);

    }


    public void insertRow(Integer calarm_id,String cloc_id,String cloc_name,String cnote) {
       SQLiteDatabase database = this.getWritableDatabase();

       // Log.d("myTag", "("+calarm_id+"--"+cloc_id+"--"+cloc_name+"--"+cnote+")");

        ContentValues values = new ContentValues();
        values.put("alarm_id",  calarm_id);
        values.put("loc_id",  cloc_id);
        values.put("loc_name", cloc_name);
        values.put("note",  cnote);
        database.insert("alarmloc", null, values);
    }

    public ArrayList getAllrec( ) {

        ArrayList <String> ar= new ArrayList<String>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c= db.rawQuery("select * from alarmloc",null);

        c.moveToFirst();

        while(! c.isAfterLast()){
            ar.add(c.getInt(0)+"--"+c.getString(1)+"--"+c.getString(2)+"--"+c.getString(3));
            c.moveToNext();
        }
        return  ar;




    }


    public void deleteRow( Integer r  ) {

        String stmt ="delete from alarmloc where alarm_id = "+r.toString();

        SQLiteDatabase sql = this.getWritableDatabase();
        sql.execSQL(stmt);

    }

    // method to delete all rows
    public void deleteAllRows() {

        String stmt2 ="delete from alarmloc";

        SQLiteDatabase sql = this.getWritableDatabase();
        sql.execSQL(stmt2);

    }


}
