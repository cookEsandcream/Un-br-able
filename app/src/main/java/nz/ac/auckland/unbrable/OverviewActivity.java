package nz.ac.auckland.unbrable;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *  Activity class representing the main screen where the diary entries are listed
 */
public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Diary Entries");
        setContentView(R.layout.activity_overview);
        
        populateEntries();
        registerClickCallback();
    }

    private void populateEntries() {
        // retrieve existing entries and populate the adapter
        DiaryEntryAdapter adapter = new DiaryEntryAdapter(this, R.layout.list_item, loadEntries());

        ListView list = (ListView) findViewById(R.id.listView);
        // inform users that the entries will go here with empty view
        list.setEmptyView(findViewById(R.id.empty_text_view));
        list.setAdapter(adapter);
    }

    private void registerClickCallback() {
        final ListView list = (ListView) findViewById(R.id.listView);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Entry entry = (Entry) list.getItemAtPosition(position);
                Bundle bundle = new Bundle();

                // set up entry details to be sent to the activity in the intent
                if(entry.getStringDate() != null) {
                    bundle.putString("date", entry.getStringDate());
                }

                if(entry.getImageUri() != null) {
                    bundle.putString("imageUri", entry.getImageUri().toString());
                }

                if(entry.getText() != null) {
                    bundle.putString("text", entry.getText());
                }

                Intent myIntent = new Intent(OverviewActivity.this, DiaryEntry.class);
                myIntent.putExtras(bundle);
                OverviewActivity.this.startActivity(myIntent);
            }
        });
    }

    public void addEntry(View v) {
        // open the edit diary entry activity
        Intent myIntent = new Intent(OverviewActivity.this, EditDiaryEntry.class);
        OverviewActivity.this.startActivity(myIntent);
    }

    private List<Entry> loadEntries() {
        // retrieve the entries from the database

        DiaryEntryDbHelper dbHelper = new DiaryEntryDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_DATE,
                DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_ENTRY,
                DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_IMAGE,
        };

        Cursor cursor = db.query(DiaryEntryContract.DiaryEntryColumns.TABLE_NAME,projection,null,null,null,null,null);
        List<Entry> results = new ArrayList<>();
        while (cursor.moveToNext()){
            long resultDate = cursor.getLong(cursor.getColumnIndex(DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_DATE));
            String resultText = cursor.getString(cursor.getColumnIndex(DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_ENTRY));
            String resultUri = cursor.getString(cursor.getColumnIndex(DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_IMAGE));
            results.add(new Entry(new Date((resultDate)), Uri.parse(resultUri), resultText));
        }

        cursor.close();
        dbHelper.close();
        return results;
    }
}
