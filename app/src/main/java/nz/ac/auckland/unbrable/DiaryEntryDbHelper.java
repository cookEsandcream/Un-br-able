package nz.ac.auckland.unbrable;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Base code from https://developer.android.com/training/basics/data-storage/databases.html
 */

public class DiaryEntryDbHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 3;
    public static final String DATABASE_NAME = "DiaryEntries.db";

    public DiaryEntryDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DiaryEntryContract.SQL_CREATE_TABLE);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Upgrade policy is to reset all data. This may be changed later.
        db.execSQL(DiaryEntryContract.SQL_DELETE_TABLE);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}
