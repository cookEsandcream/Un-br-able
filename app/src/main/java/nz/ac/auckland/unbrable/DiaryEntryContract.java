package nz.ac.auckland.unbrable;

import android.provider.BaseColumns;

/**
 * Base code from https://developer.android.com/training/basics/data-storage/databases.html
 */
public final class DiaryEntryContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DiaryEntryContract() {}

    /* Inner class that defines the table contents */
    public static class DiaryEntryColumns implements BaseColumns {
        public static final String TABLE_NAME = "Entries";
        public static final String COLUMN_NAME_BITMAP = "Image_Bitmap";
        public static final String COLUMN_NAME_ENTRY = "Entry_Text";
        public static final String COLUMN_NAME_DATE = "Date";
    }

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + DiaryEntryColumns.TABLE_NAME + " (" +
                    DiaryEntryColumns._ID + " INTEGER PRIMARY KEY," +
                    DiaryEntryColumns.COLUMN_NAME_BITMAP + " TEXT," +
                    DiaryEntryColumns.COLUMN_NAME_ENTRY + " TEXT," +
                    DiaryEntryColumns.COLUMN_NAME_DATE + " INTEGER);";
    public static final String SQL_DELETE_TABLE =
            "DROP TABLE IF EXISTS " + DiaryEntryColumns.TABLE_NAME;


}
