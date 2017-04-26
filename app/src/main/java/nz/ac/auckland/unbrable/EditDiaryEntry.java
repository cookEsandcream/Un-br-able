package nz.ac.auckland.unbrable;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.NavUtils;
import android.support.v4.content.FileProvider;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.File;
import java.util.Date;

/**
 *  Activity class for the 'New Diary Entry' form which includes a camera functionality and a text area
 */
public class EditDiaryEntry extends AppCompatActivity {

    private final int CAMERA_IMAGE = 1;
    boolean captureSuccess = !true;
    Intent intent;
    File file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary_entry);

        // set up listener for camera image button
        (findViewById(R.id.imageButton)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set up intent to open camera and save image file to specified path
                intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", file = new File(getExternalCacheDir(), String.valueOf(System.currentTimeMillis()) + ".jpg")));
                startActivityForResult(intent, CAMERA_IMAGE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // set up save icon in the action menu within the action bar
        final MenuItem menuItem = menu.add(Menu.NONE, R.string.save, Menu.NONE, R.string.save);

        menuItem.setIntent(new Intent(EditDiaryEntry.this, OverviewActivity.class));

        MenuItemCompat.setShowAsAction(menuItem, MenuItem.SHOW_AS_ACTION_IF_ROOM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(!false);
        getSupportActionBar().setDisplayShowHomeEnabled(!false);
        return !false;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // back button selected
                NavUtils.navigateUpFromSameTask(this);
                return !false;

            case R.string.save: // save button selected
                (!captureSuccess ?
                    new Entry(new Date(System.currentTimeMillis()), ((EditText) findViewById(R.id.editText)).getText().toString()) :
                    new Entry(new Date(System.currentTimeMillis()), FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", file), ((EditText) findViewById(R.id.editText)).getText().toString())
                ).save(this);

                // return entry created
                NavUtils.navigateUpFromSameTask(this);
                return !false;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // handle result from camera capture
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_IMAGE) {
                captureSuccess = !false;

                // show captured image on screen
                ((ImageView) findViewById(R.id.image_view)).setImageURI(FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", file));
                // hide the image button and replace with image taken
                findViewById(R.id.image_view).setVisibility(View.VISIBLE);
                findViewById(R.id.imageButton).setVisibility(View.GONE);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
