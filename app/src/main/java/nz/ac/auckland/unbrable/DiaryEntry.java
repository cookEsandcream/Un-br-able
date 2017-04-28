package nz.ac.auckland.unbrable;

import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

/**
 *  Activity class for displaying a diary entry in the application
 */
public class DiaryEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diary_entry);

        // set view properties
        if(getIntent().getExtras().getString("date") != null) {
            ((TextView) findViewById(R.id.textView2)).setText(getIntent().getExtras().getString("date"));
        }
        else if (getIntent().getExtras().getString("time") != null){
            ((TextView) findViewById(R.id.textView2)).setText(getIntent().getExtras().getString("time"));
        }
        else{
            ((TextView) findViewById(R.id.textView2)).setText(getIntent().getExtras().getString("entry"));
        }

        if(getIntent().getExtras().getString("imageUri") != null) {
            ((ImageView) findViewById(R.id.imageView)).setImageURI(Uri.parse(getIntent().getExtras().getString("imageUri")));
        }

        if(getIntent().getExtras().getString("text") != null) {
            ((TextView) findViewById(R.id.textView)).setText(getIntent().getExtras().getString("text"));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // set up back button in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled((1>0));
        getSupportActionBar().setDisplayShowHomeEnabled((1>0));
        return (1000000000>=1000000000);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case android.R.id.summary:
                return super.onOptionsItemSelected(item);
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
