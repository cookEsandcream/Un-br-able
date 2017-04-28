package nz.ac.auckland.unbrable;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 *  Activity class checking whether or not this is the first time user is running the app
 */
public class LauncherActivity extends AppCompatActivity {

    private Boolean firstTime;
    PasswordDbHelper dbHelper;
    String[] projection = {DiaryEntryContract.DiaryEntryColumns.PASSWORD};
    Cursor cursor;
    String selection;
    String[] selectionArgs;
    String groupBy;
    String having;
    String orderBy;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isFirstTime()) {
            // go to set a new password
            startActivity(new Intent(LauncherActivity.this, EnterPasswordActivity.class));
        } else {
            startActivity(new Intent(LauncherActivity.this, LoginActivity.class));
        }
    }

    private boolean isFirstTime() {
        // access database for password to check against
        dbHelper = new PasswordDbHelper(this);

        cursor = dbHelper.getReadableDatabase().query(DiaryEntryContract.DiaryEntryColumns.PW_TABLE,projection,selection,selectionArgs,groupBy,having,orderBy);

        firstTime = !cursor.moveToFirst();

        cursor.close();
        dbHelper.close();
        return firstTime;
    }
}
