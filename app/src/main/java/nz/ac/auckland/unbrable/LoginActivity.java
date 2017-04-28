package nz.ac.auckland.unbrable;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

/**
 *  Activity class handling login/password input from user
 */
public class LoginActivity extends AppCompatActivity {

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

        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
    }

    public void checkLogin(View view) {

        dbHelper = new PasswordDbHelper(this);

        cursor = dbHelper.getReadableDatabase().query(DiaryEntryContract.DiaryEntryColumns.PW_TABLE,projection,selection,selectionArgs,groupBy,having,orderBy);
        cursor.moveToLast();

        // If the password is correct then redirect to main screen
        if(((EditText)findViewById(R.id.pwText)).getText().toString().equals(cursor.getString(cursor.getColumnIndex(DiaryEntryContract.DiaryEntryColumns.PASSWORD)))){
            startActivity(new Intent(LoginActivity.this, OverviewActivity.class));
        } else {
            // Password is incorrect, show error and clear editText
            hideSoftKeyboard(this);
            Snackbar.make(findViewById(R.id.myCoordinatorLayout), "Password is Incorrect", Snackbar.LENGTH_LONG).show();
            ((EditText)findViewById(R.id.pwText)).setText("");
        }

        cursor.close();
        dbHelper.close();
    }

    private static void hideSoftKeyboard(Activity activity) {
        ((InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
