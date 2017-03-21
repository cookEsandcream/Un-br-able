package nz.ac.auckland.unbrable;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

public class EditDiaryEntry extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_diary_entry);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuItem menuItem = menu.add(Menu.NONE, 1000, Menu.NONE, R.string.save);

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
        }
        return super.onOptionsItemSelected(item);
    }
}
