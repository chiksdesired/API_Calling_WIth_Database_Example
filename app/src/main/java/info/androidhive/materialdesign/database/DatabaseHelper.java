package info.androidhive.materialdesign.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import info.androidhive.materialdesign.R;


public class DatabaseHelper extends SQLiteOpenHelper {

    private static SQLiteDatabase myDataBase;
    private static Context myContext;
    public static Cursor cursor;
    String myPath_d = "";
    public static String TABLE_NAME = "Info";


    public DatabaseHelper(Context context) {

        super(context, context.getResources().getString(R.string.DB_NAME),
                null, 1);
        myContext = context;

        if (android.os.Build.VERSION.SDK_INT >= 17) {
            myPath_d = context.getApplicationInfo().dataDir + "/databases/";
        } else {
            myPath_d = "/data/data/" + context.getPackageName() + "/databases/";
        }

    }

    // ---Create the database---
    public void createDataBase() throws IOException {

        // ---Check whether database is already created or not---
        boolean dbExist = checkDataBase();

        if (!dbExist) {
            this.getReadableDatabase();
            try {
                // ---If not created then copy the database---
                copyDataBase();

            } catch (IOException e) {

                throw new RuntimeException(e);

            }
        }
    }

    // --- Check whether database already created or not---
    public boolean checkDataBase() {
        try {
            String myPath = myPath_d
                    + myContext.getString(R.string.DB_NAME);
            File f = new File(myPath);
            if (f.exists())
                return true;
            else
                return false;
        } catch (SQLiteException e) {
            e.printStackTrace();
            return false;
        }
    }

    // --- Copy the database to the output stream---
    private void copyDataBase() throws IOException {

        InputStream myInput = myContext.getAssets().open(
                myContext.getString(R.string.DB_NAME));

        String outFileName = myPath_d
                + myContext.getString(R.string.DB_NAME);

        OutputStream myOutput = new FileOutputStream(outFileName);

        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }
        myOutput.flush();
        myOutput.close();
        myInput.close();
    }

    public boolean CheckIsDataAlreadyInDBorNot() {
        SQLiteDatabase sqldb = DatabaseHelper.myDataBase;
        String Query = "Select * from " + TABLE_NAME;
        Cursor cursor = sqldb.rawQuery(Query, null);
        if (cursor.getCount() <= 0) {
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }

    public void openDataBase() throws SQLException {

        // --- Open the database---.

        String myPath = myPath_d
                + myContext.getString(R.string.DB_NAME);

        myDataBase = SQLiteDatabase.openDatabase(myPath, null,
                SQLiteDatabase.OPEN_READWRITE);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();
        super.close();
    }

    @Override
    public void onCreate(SQLiteDatabase arg0) {
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public void insertUserDataInfo(String userid, String mac_address, Double latitude, Double longitude, String date, String time, String address, int flag, String bledevicename, String timezone) {
        ContentValues values;
        values = new ContentValues();

        values.put("userid", userid);
        values.put("mac_address", mac_address);
        values.put("latitude", latitude);
        values.put("longitude", longitude);
        values.put("date", date);
        values.put("time", time);
        values.put("address", address);
        values.put("flag", flag);
        values.put("bledevicename", bledevicename);
        values.put("time_zone", timezone);

        if (isExist(mac_address)) {
            myDataBase.update(TABLE_NAME, values, "mac_address = '" + mac_address + "'", null);
        } else {
            myDataBase.insert(TABLE_NAME, null, values);
        }
//        myDataBase.insert("User_Details", null, values);
        SQLiteDatabase.releaseMemory();
    }

    public boolean isExist(String mac_address) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE mac_address = '" + mac_address + "'", null);
        boolean exist = (cur.getCount() > 0);
        cur.close();
        db.close();
        return exist;

    }

    public void insertUserDataContact(String name, String email_id, String contact_number) {
        ContentValues values;
        values = new ContentValues();

        values.put("name", name);
        values.put("email_id", email_id);
        values.put("contact_number", contact_number);

        myDataBase.insert(TABLE_NAME, null, values);

        Log.e("values","values"+values);


//        myDataBase.insert("User_Details", null, values);
        SQLiteDatabase.releaseMemory();
    }


   /* public ArrayList<PassiveModel> getpasivedata() {
        ArrayList<PassiveModel> savedModels = new ArrayList<PassiveModel>();

        PassiveModel savedModel;

        cursor = myDataBase.rawQuery("select * from User_Details", null);

        if (cursor != null) {
            cursor.moveToFirst();
            for (int i = 0; i < cursor.getCount(); i++) {
                savedModel = new PassiveModel();
                savedModel.setMac_address(cursor.getString(0));
                savedModel.setLatitude(cursor.getDouble(1));
                savedModel.setLongitude(cursor.getDouble(2));
                savedModel.setDate(cursor.getString(3));
                savedModel.setTime(cursor.getString(4));
                savedModel.setAddress(cursor.getString(5));
                savedModel.setFlag(cursor.getInt(6));
                savedModel.setBledevicename(cursor.getString(7));
                savedModel.setUserid(cursor.getString(8));
                savedModel.setTime_zone(cursor.getString(9));
                savedModels.add(savedModel);
                cursor.moveToNext();
            }

        }
        cursor.close();
        SQLiteDatabase.releaseMemory();
        return savedModels;
    }

    public void deleteEntry() {
        // TODO Auto-generated method stub
        myDataBase.execSQL("delete from " + TABLE_NAME);
    }*/
}