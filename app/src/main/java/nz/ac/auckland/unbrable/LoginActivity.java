package nz.ac.auckland.unbrable;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity {

    EditText pwText;
    String incorrectPwMessage = "Password is Incorrect";

    Snackbar incorrectPasswordSnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);

        incorrectPasswordSnackbar = Snackbar.make(findViewById(R.id.myCoordinatorLayout),
                incorrectPwMessage, Snackbar.LENGTH_LONG);
    }

    public void checkLogin(View view) {

        pwText = (EditText)findViewById(R.id.pwText);
        Log.d("Input",pwText.getText().toString());

        PasswordDbHelper dbHelper = new PasswordDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {DiaryEntryContract.DiaryEntryColumns.PASSWORD};

        Cursor cursor = db.query(DiaryEntryContract.DiaryEntryColumns.PW_TABLE,projection,null,null,null,null,null);
        cursor.moveToLast();
        String resultText = cursor.getString(cursor.getColumnIndex(DiaryEntryContract.DiaryEntryColumns.PASSWORD));


        //If the password is correct then redirect to screen
        if(pwText.getText().toString().equals(resultText)){
            Log.d("Tag","password is true");

            //switch to overview activity
            Intent myIntent = new Intent(LoginActivity.this, OverviewActivity.class);
            startActivity(myIntent);
        }
        //Password is incorrect
        else{
            Log.d("Tag","password is false");
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

    private String readFromAssetFile(){
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(
                    new InputStreamReader(getAssets().open("PasswordFile.txt"), "UTF-8"));

            return reader.readLine();

            // do reading, usually loop until end of file reading
//            String mLine;
//            while ((mLine = reader.readLine()) != null) { }
        } catch (IOException e) {
            //log the exception
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    Log.e("FileReadError","File could not be read");
                }
            }
        }
        return null;
    }
}
