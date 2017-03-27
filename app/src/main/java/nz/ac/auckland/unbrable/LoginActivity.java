package nz.ac.auckland.unbrable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class LoginActivity extends AppCompatActivity {

    EditText pwText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
    }

    public void checkLogin(View view) {

        pwText = (EditText)findViewById(R.id.pwText);
        Log.d("Input",pwText.getText().toString());

        String password = readFromAssetFile();
        Log.d("Password",password);

        //If the password is correct then redirect to screen
        if(pwText.getText().toString().equals(password)){
            Log.d("Tag","password is true");

            //switch to overview activity
            Intent myIntent = new Intent(LoginActivity.this, OverviewActivity.class);
            startActivity(myIntent);
        }
        //Password is incorrect
        else{
            Log.d("Tag","password is false");
            pwText.setText("");
        }


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
