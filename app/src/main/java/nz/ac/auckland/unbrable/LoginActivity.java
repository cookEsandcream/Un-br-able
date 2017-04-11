package nz.ac.auckland.unbrable;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

    EditText pwText;
    String incorrectPwMessage = "Password is Incorrect";

    Snackbar incorrectPasswordSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        // set up error display snackbar
        incorrectPasswordSnackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                incorrectPwMessage, Snackbar.LENGTH_LONG);
    }

    public void checkLogin(View view) {
        pwText = (EditText)findViewById(R.id.pwText);
//        Log.d("Input",pwText.getText().toString());

        // access database for password to check against
        PasswordDbHelper dbHelper = new PasswordDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {DiaryEntryContract.DiaryEntryColumns.PASSWORD};

        Cursor cursor = db.query(DiaryEntryContract.DiaryEntryColumns.PW_TABLE,projection,null,null,null,null,null);
        cursor.moveToLast();
        String resultText = cursor.getString(cursor.getColumnIndex(DiaryEntryContract.DiaryEntryColumns.PASSWORD));

        // If the password is correct then redirect to main screen
        if(pwText.getText().toString().equals(resultText)){
            Intent myIntent = new Intent(LoginActivity.this, OverviewActivity.class);
            startActivity(myIntent);
        } else {
            // Password is incorrect, show error and clear editText
            incorrectPasswordSnackbar.show();
            hideSoftKeyboard(this);
            pwText.setText("");
        }

        cursor.close();
        dbHelper.close();
    }

    private static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }
}
