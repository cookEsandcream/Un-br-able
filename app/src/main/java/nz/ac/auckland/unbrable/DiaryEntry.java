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

        // retrieve entry details from intent
        Bundle bundle = getIntent().getExtras();

        String date = bundle.getString("date");
        String imageUri = bundle.getString("imageUri");
        String text = bundle.getString("text");

        // set view properties
        TextView dateView = (TextView) findViewById(R.id.textView2);
        if(date != null) {
            dateView.setText(date);
        }

        ImageView imageView = (ImageView) findViewById(R.id.imageView);
        if(imageUri != null) {
            imageView.setImageURI(Uri.parse(imageUri));
        }

        TextView textView = (TextView) findViewById(R.id.textView);
        if(text != null) {
            textView.setText(text);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // set up back button in the action bar
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
