package nz.ac.auckland.unbrable;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 *  Activity class handling the first run after installation, allows users to set a password for the application that will be used
 *  to  unlock the diary later
 */
public class EnterPasswordActivity extends AppCompatActivity {

    PasswordDbHelper dbHelper;
    ContentValues values;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_enter_password);
    }

    public void savePassword(View view) {

        // validate the passwords and show errors where required
        if (((EditText)findViewById(R.id.pwText)).getText().toString().length() >= 4) {
            if (((EditText)findViewById(R.id.pwText)).getText().toString().equals(((EditText)findViewById(R.id.pwText2)).getText().toString())) {
                storePassword(((EditText)findViewById(R.id.pwText)).getText().toString());
                startActivity(new Intent(EnterPasswordActivity.this, OverviewActivity.class));
            } else {
                Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                        "Passwords don't match!", Snackbar.LENGTH_LONG).show();
                hideSoftKeyboard(this);
                ((EditText)findViewById(R.id.pwText)).setText("");
                ((EditText)findViewById(R.id.pwText2)).setText("");
            }
        } else {
            Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                    "Password must be at least 4 characters long", Snackbar.LENGTH_LONG).show();
            hideSoftKeyboard(this);
            ((EditText)findViewById(R.id.pwText)).setText("");
            ((EditText)findViewById(R.id.pwText2)).setText("");
        }
    }

    private static void hideSoftKeyboard(Activity activity) {
        ((InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    private void storePassword(String password){
        dbHelper = new PasswordDbHelper(this);

        // add password to database
        values = new ContentValues();
        values.put(DiaryEntryContract.DiaryEntryColumns.PASSWORD, password);

        dbHelper.getWritableDatabase().insert(DiaryEntryContract.DiaryEntryColumns.PW_TABLE,null,values);
        dbHelper.close();
    }
}
