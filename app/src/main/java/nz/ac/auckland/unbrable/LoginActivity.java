package nz.ac.auckland.unbrable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_login);
    }

    public void login(View view) {
        Intent myIntent = new Intent(LoginActivity.this, OverviewActivity.class);
        startActivity(myIntent);
    }
}
