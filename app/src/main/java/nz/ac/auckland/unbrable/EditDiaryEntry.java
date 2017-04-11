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
    ImageButton cameraButton;
    boolean captureSuccess = false;
    Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary_entry);

        // set up listener for camera image button
        cameraButton = (ImageButton) findViewById(R.id.imageButton);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // set up intent to open camera and save image file to specified path
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                File file = new File(getExternalCacheDir(), String.valueOf(System.currentTimeMillis()) + ".jpg");
                fileUri = FileProvider.getUriForFile(getApplicationContext(), getApplicationContext().getPackageName() + ".provider", file);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, CAMERA_IMAGE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // set up save icon in the action menu within the action bar
        final MenuItem menuItem = menu.add(Menu.NONE, R.string.save, Menu.NONE, R.string.save);

        Intent myIntent = new Intent(EditDiaryEntry.this, OverviewActivity.class);
        menuItem.setIntent(myIntent);

        MenuItemCompat.setShowAsAction(menuItem, MenuItem.SHOW_AS_ACTION_IF_ROOM);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home: // back button selected
                NavUtils.navigateUpFromSameTask(this);
                return true;

            case R.string.save: // save button selected
                Entry thisEntry;
                if (!captureSuccess){
                    // capture failed or no picture, create a diary entry with no picture
                    thisEntry  = new Entry(
                            new Date(System.currentTimeMillis()),
                            ((EditText) findViewById(R.id.editText)).getText().toString()
                    );
                } else {
                    thisEntry = new Entry(
                            new Date(System.currentTimeMillis()),
                            fileUri,
                            ((EditText) findViewById(R.id.editText)).getText().toString()
                    );
                }

                // return entry created
                thisEntry.save(this);
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // handle result from camera capture
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_IMAGE) {
                captureSuccess = true;

                // show captured image on screen
                ImageView imageView = (ImageView) findViewById(R.id.image_view);
                imageView.setImageURI(fileUri);
                // hide the image button and replace with image taken
                imageView.setVisibility(View.VISIBLE);
                cameraButton.setVisibility(View.GONE);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
