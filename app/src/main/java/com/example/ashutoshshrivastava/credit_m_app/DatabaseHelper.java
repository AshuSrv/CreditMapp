package com.example.ashutoshshrivastava.credit_m_app;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by csa on 3/1/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {


    public static String DATABASE = "database.db3";
    public static String TABLE ="Final";
    public static String NAME ="name";
    public static String COMPANY ="company";
    public static String CITY ="city";
    public static String CREDIT ="credit";
    String br;

    public DatabaseHelper(@Nullable Context context, @Nullable String
            name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //  br= "CREATE TABLE mytable(name TEXT,company TEXT,city TEXT,country TEXT);";
        br = "CREATE TABLE "+TABLE+"("+NAME+ " Text, "+COMPANY+ " Text, "+CITY+ " Text, "+CREDIT+ " Integer);";
        db.execSQL(br);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE+" ;");
        onCreate(db);
    }


    public void insertdata(String name,String company ,String city,int credit){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues= new ContentValues();


        contentValues.put(NAME, name);
        contentValues.put(COMPANY, company);
        contentValues.put(CITY,city);
        contentValues.put(CREDIT,credit);
        db.insert(TABLE,null,contentValues);


    }



    public List<DataModel> getdata(){
        // DataModel dataModel = new DataModel();
        List<DataModel> data=new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE+" ;",null);
        StringBuffer stringBuffer = new StringBuffer();
        DataModel dataModel = null;
        while (cursor.moveToNext()) {
            dataModel= new DataModel();
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int credit = cursor.getInt(cursor.getColumnIndexOrThrow("credit"));
            String city = cursor.getString(cursor.getColumnIndexOrThrow("city"));
            String company = cursor.getString(cursor.getColumnIndexOrThrow("company"));
            dataModel.setName(name);
            dataModel.setCity(city);
            dataModel.setCredit(credit);
            dataModel.setCompany(company);
            stringBuffer.append(dataModel);
            // stringBuffer.append(dataModel);
            data.add(dataModel);
        }

        for (DataModel mo:data ) {

            Log.i("Hellomo",""+mo.getCity());
        }

        //

        return data;
    }


    public void update(String name,int num)
    {
        SQLiteDatabase db=getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(CREDIT, num);
        db.update(TABLE,contentValues ,"name='"+name+"'" ,null );
    }



    public void deleteTitle(String name)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(TABLE, "name='"+name+"'" , null);
    }


    public List<DataModel> showReciever(String recieverName)
    {
        List<DataModel> data=new ArrayList<>();

        SQLiteDatabase db=getWritableDatabase();
        Cursor cursor=db.rawQuery("SELECT * FROM "+TABLE+" WHERE name='"+recieverName+"';",null);

        StringBuffer stringBuffer=new StringBuffer();

        while (cursor.moveToNext())
        {
            DataModel dataModel=new DataModel();

            String name=cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int num=cursor.getInt(cursor.getColumnIndexOrThrow("credit"));

            dataModel.setName(name);
            dataModel.setCredit(num);

            stringBuffer.append(dataModel);
            data.add(dataModel);
        }

        return data;

    }

    //spiner start//

    public ArrayList<String> getAllProvinces(){

        ArrayList<String> list = new ArrayList<String>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+TABLE+" ;",null);
        if (cursor.getCount() > 0){

            while (cursor.moveToNext()){

                String name = cursor.getString(cursor.getColumnIndex("name"));
                list.add(name);

            }
        }
        return list;
    }

//spiner end//

}