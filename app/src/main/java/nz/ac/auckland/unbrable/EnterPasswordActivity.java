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

    private String incorrectPwMessage = "Passwords don't match!";
    private String pwLengthMessage = "Password must be at least 4 characters long";
    private Snackbar passwordMatchSnackbar;
    private Snackbar passwordLengthSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_enter_password);

        // set up snackbars used to show error messages to user
        passwordMatchSnackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                incorrectPwMessage, Snackbar.LENGTH_LONG);
        passwordLengthSnackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                pwLengthMessage, Snackbar.LENGTH_LONG);
    }

    public void savePassword(View view) {
        // get passwords from editText
        EditText pwText = (EditText)findViewById(R.id.pwText);
        String password = pwText.getText().toString();

        EditText confText = (EditText)findViewById(R.id.pwText2);
        String confirmPassword = confText.getText().toString();

        // validate the passwords and show errors where required
        if (password.length() >= 4) {
            if (password.equals(confirmPassword)) {
                storePassword(password);
                Intent myIntent = new Intent(EnterPasswordActivity.this, OverviewActivity.class);
                startActivity(myIntent);
            } else {
                passwordMatchSnackbar.show();
                hideSoftKeyboard(this);
                pwText.setText("");
                confText.setText("");
            }
        } else {
            passwordLengthSnackbar.show();
            hideSoftKeyboard(this);
            pwText.setText("");
            confText.setText("");
        }
    }

    private static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager = (InputMethodManager)activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
    }

    private void storePassword(String password){
        PasswordDbHelper dbHelper = new PasswordDbHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // add password to database
        ContentValues values = new ContentValues();
        values.put(DiaryEntryContract.DiaryEntryColumns.PASSWORD, password);

        db.insert(DiaryEntryContract.DiaryEntryColumns.PW_TABLE,null,values);
        dbHelper.close();
    }
}
