package familyfriday.apartmentlist.com.adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import beans.NameBean;

/**
 * Created by vikra on 3/11/2018.
 */

public class FamilyDatabaseAdapter {


    private static final String DB_NAME="nameList.db";
    private static final int DB_VERSION=1;

    private static final String TABLE="tableMember";
    private static final String COLUMN_NAMES="names";
    private static final String COLUMN_ID ="columnId";

/*

Creating table with 2 columns:
1. Primary key
2. Names of employees
 */

    private static String CREATE_TABLE ="CREATE TABLE "+TABLE+"("+ COLUMN_ID +" INTEGER PRIMARY KEY, "
            + COLUMN_NAMES+" TEXT NOT NULL)";



    private Context context;
    private SQLiteDatabase sqLliteDatabase;
    private static FamilyDatabaseAdapter familyDatabaseAdapterInstance;


    private FamilyDatabaseAdapter(Context context){
        this.context=context;
        sqLliteDatabase=new NamesDBHelper(this.context,DB_NAME,null,DB_VERSION).getWritableDatabase();
    }
/*

Singleton implementation of Database
 */
    public  static FamilyDatabaseAdapter getNameListDBAdapterInstance(Context context){
        if(familyDatabaseAdapterInstance==null){
            familyDatabaseAdapterInstance=new FamilyDatabaseAdapter(context);
        }
        return familyDatabaseAdapterInstance;
    }

    /*

    insert,delete,query methods
     */

    public boolean insertName(String name){
        ContentValues contentValues=new ContentValues();
        contentValues.put(COLUMN_NAMES,name);

        return sqLliteDatabase.insert(TABLE,null,contentValues)>0;
    }

    public boolean deleteName(Long nameId){
        return sqLliteDatabase.delete(TABLE, COLUMN_ID+" = "+nameId,null)>0;
    }
/*

Method to get names with rowID
 */

    public List<NameBean> getAllNames(){
        List<NameBean> nameList=new ArrayList<NameBean>();

        Cursor cursor=sqLliteDatabase.query(TABLE,new String[]{COLUMN_ID,COLUMN_NAMES},null,null,null,null,null,null);


        if(cursor!=null &cursor.getCount()>0){
            while(cursor.moveToNext()){
                NameBean nameBean=new NameBean(cursor.getLong(0),cursor.getString(1));
                 nameList.add(nameBean);

            }
        }
        cursor.close();
        return  nameList;
    }
/*

Method to get names only instead of bean with rowID
 */
    public List<String> getOnlyNames(){
        List<String> nameList=new ArrayList<String>();

        Cursor cursor=sqLliteDatabase.query(TABLE,new String[]{COLUMN_ID,COLUMN_NAMES},null,null,null,null,null,null);


        if(cursor!=null &cursor.getCount()>0){
            while(cursor.moveToNext()){
                NameBean nameBean=new NameBean(cursor.getLong(0),cursor.getString(1));
                nameList.add(nameBean.getName());

            }
        }
        cursor.close();
        return  nameList;
    }


    private static class NamesDBHelper extends SQLiteOpenHelper {

        public NamesDBHelper(Context context, String databaseName, SQLiteDatabase.CursorFactory factory, int dbVersion){
            super(context,databaseName,factory,dbVersion);
        }
        @Override
        public void onCreate(SQLiteDatabase sqLiteDatabase) {
            sqLiteDatabase.execSQL(CREATE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase,
                              int oldVersion,final int newVersion) {

            }
        }
}
