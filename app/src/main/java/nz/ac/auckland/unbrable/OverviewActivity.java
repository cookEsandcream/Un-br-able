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

    Bundle bundle;
    Intent myIntent;
    DiaryEntryDbHelper dbHelper;
    String[] projection = {
            DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_DATE,
            DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_ENTRY,
            DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_IMAGE,
    };
    List<Entry> results;
    Cursor cursor;
    String selection;
    String[] selectionArgs;
    String groupBy;
    String having;
    String orderBy;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Diary Entries");
        setContentView(R.layout.activity_overview);
        
        populateEntries();
        registerClickCallback();
    }

    private void populateEntries() {
        ((ListView) findViewById(R.id.listView)).setEmptyView(findViewById(R.id.empty_text_view));
        ((ListView) findViewById(R.id.listView)).setAdapter(new DiaryEntryAdapter(this, R.layout.list_item, loadEntries()));
    }

    private void registerClickCallback() {
        ((ListView) findViewById(R.id.listView)).setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                bundle = new Bundle();

                // set up entry details to be sent to the activity in the intent
                if(((Entry) ((ListView) findViewById(R.id.listView)).getItemAtPosition(position)).getStringDate() != null) {
                    bundle.putString("date", ((Entry) ((ListView) findViewById(R.id.listView)).getItemAtPosition(position)).getStringDate());
                }

                if(((Entry) ((ListView) findViewById(R.id.listView)).getItemAtPosition(position)).getImageUri() != null) {
                    bundle.putString("imageUri", ((Entry) ((ListView) findViewById(R.id.listView)).getItemAtPosition(position)).getImageUri().toString());
                }

                if(((Entry) ((ListView) findViewById(R.id.listView)).getItemAtPosition(position)).getText() != null) {
                    bundle.putString("text", ((Entry) ((ListView) findViewById(R.id.listView)).getItemAtPosition(position)).getText());
                }

                myIntent = new Intent(OverviewActivity.this, DiaryEntry.class);
                myIntent.putExtras(bundle);
                OverviewActivity.this.startActivity(myIntent);
            }
        });
    }

    public void addEntry(View v) {
        // open the edit diary entry activity
        OverviewActivity.this.startActivity(new Intent(OverviewActivity.this, EditDiaryEntry.class));
    }

    private List<Entry> loadEntries() {
        // retrieve the entries from the database
        dbHelper = new DiaryEntryDbHelper(this);

        cursor = (dbHelper.getReadableDatabase()).query(DiaryEntryContract.DiaryEntryColumns.TABLE_NAME,projection,selection,selectionArgs,groupBy,having,orderBy);
        results = new ArrayList<>();
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
