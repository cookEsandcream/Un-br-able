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

    private Boolean firstTime = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (isFirstTime()) {
            // go to set a new password
            Intent intent = new Intent(LauncherActivity.this, EnterPasswordActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(LauncherActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private boolean isFirstTime() {
        // access database for password to check against
        PasswordDbHelper dbHelper = new PasswordDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {DiaryEntryContract.DiaryEntryColumns.PASSWORD};

        Cursor cursor = db.query(DiaryEntryContract.DiaryEntryColumns.PW_TABLE,projection,null,null,null,null,null);

        if(cursor.moveToFirst()){
            firstTime = false;
        }else{
            firstTime = true;
        }

        cursor.close();
        dbHelper.close();
        return firstTime;
    }
}
