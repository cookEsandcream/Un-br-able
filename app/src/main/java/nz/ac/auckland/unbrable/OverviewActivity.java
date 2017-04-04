package nz.ac.auckland.unbrable;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

        List<Entry> entries = LoadEntries();
        if (entries.isEmpty()){
            entries.add(new Entry(new Date(System.currentTimeMillis()), "My First Entry"));
        }

        DiaryEntryAdapter adapter = new DiaryEntryAdapter(this, R.layout.list_item, entries);

        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
    }

    private void registerClickCallback() {

        final ListView list = (ListView) findViewById(R.id.listView);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Entry entry = (Entry) list.getItemAtPosition(position);

                Bundle bundle = new Bundle();

                if(entry.getStringDate() != null) {
                    bundle.putString("date", entry.getStringDate());
                }

                if(entry.getImageBitmap() != null) {
                    bundle.putString("imageBitmap", entry.bitmapToString(entry.getImageBitmap()));
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
        Intent myIntent = new Intent(OverviewActivity.this, EditDiaryEntry.class);
        OverviewActivity.this.startActivity(myIntent);
    }

    private List<Entry> LoadEntries(){
        DiaryEntryDbHelper dbHelper = new DiaryEntryDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        String[] projection = {
                DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_DATE,
                DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_ENTRY,
                DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_BITMAP,
        };
        Cursor cursor = db.query(DiaryEntryContract.DiaryEntryColumns.TABLE_NAME,projection,null,null,null,null,null);
        List<Entry> results = new ArrayList<>();
        while (cursor.moveToNext()){
            long resultDate = cursor.getLong(cursor.getColumnIndex(DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_DATE));
            String resultText = cursor.getString(cursor.getColumnIndex(DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_ENTRY));
            String resultBitmap = cursor.getString(cursor.getColumnIndex(DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_BITMAP));
            results.add(new SerialisedEntry(resultDate,resultBitmap,resultText));
        }
        cursor.close();
        dbHelper.close();
        return results;
    }
}
