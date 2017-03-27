package nz.ac.auckland.unbrable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.support.design.widget.Snackbar;


public class LoginActivity extends AppCompatActivity {

    EditText pwText;
    String password = "password";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
    }

    public void checkLogin(View view) {

        pwText = (EditText)findViewById(R.id.pwText);

        //If the password is correct then redirect to screen
        if(pwText.toString().equals(password)){
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
}
