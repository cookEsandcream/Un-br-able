package nz.ac.auckland.unbrable;

import android.provider.BaseColumns;

/**
 * Base code from https://developer.android.com/training/basics/data-storage/databases.html
 */
public final class  DiaryEntryContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DiaryEntryContract() {}

    /* Inner class that defines the table contents */
    public static class DiaryEntryColumns implements BaseColumns {
        public static final String TABLE_NAME = "Entries";
        public static final String COLUMN_NAME_IMAGE = "Image_Uri";
        public static final String COLUMN_NAME_ENTRY = "Entry_Text";
        public static final String COLUMN_NAME_DATE = "Date";
        public static final String PW_TABLE = "Password_Table";
        public static final String PASSWORD = "Password";
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + DiaryEntryColumns.TABLE_NAME + " (" +
                    DiaryEntryColumns._ID + " INTEGER PRIMARY KEY," +
                    DiaryEntryColumns.COLUMN_NAME_IMAGE + " TEXT," +
                    DiaryEntryColumns.COLUMN_NAME_ENTRY + " TEXT," +
                    DiaryEntryColumns.COLUMN_NAME_DATE + " INTEGER);";

    public static final String SQL_CREATE_PW_TABLE =
            "CREATE TABLE " + DiaryEntryColumns.PW_TABLE + " (" +
                    DiaryEntryColumns._ID + " INTEGER PRIMARY KEY," +
                    DiaryEntryColumns.PASSWORD + " TEXT);" ;

    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + DiaryEntryColumns.TABLE_NAME;


}
