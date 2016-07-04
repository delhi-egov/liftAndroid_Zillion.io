package com.zillion.delhibelly.liftsManager.Databases;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @author Time Traveller
 */

public class AppDatabases extends SQLiteOpenHelper {

    public String appUserInfoqry = "Create Table if not exists " + "userInfo" + "(request_month text, request_date text, request_timeslot text, request_items text);";

    public String appUserTxTable = "Create Table if not exists " + "userTxTable"
            + "(user_id real, tx_num text, tx_address text, tx_scheduled_date real, tx_scheduled_time real, tx_items real, tx_status real);";

    public AppDatabases(Context context, String DBname, CursorFactory factory, int version) {
        super(context, DBname, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        try {
            db.execSQL(appUserInfoqry);
            db.execSQL(appUserTxTable);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion < 2) {

        }
    }
}