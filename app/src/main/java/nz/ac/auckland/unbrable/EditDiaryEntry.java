package nz.ac.auckland.unbrable;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
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
import java.io.IOException;
import java.util.Date;

public class EditDiaryEntry extends AppCompatActivity {

    private final int CAMERA_IMAGE = 1;
    ImageButton cameraButton;
    boolean captureSuccess = false;
    Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary_entry);

        cameraButton = (ImageButton) findViewById(R.id.imageButton);
        cameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            // Save button functionality
            case R.string.save:
                Entry thisEntry;

                if (!captureSuccess){
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

                thisEntry.save(this);
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            if (requestCode == CAMERA_IMAGE) {
                captureSuccess = true;
                    ImageView imageView = (ImageView) findViewById(R.id.image_view);
                    imageView.setImageURI(fileUri);
                    imageView.setVisibility(View.VISIBLE);
                    cameraButton.setVisibility(View.GONE);
            }
        }

        super.onActivityResult(requestCode, resultCode, data);
    }
}
