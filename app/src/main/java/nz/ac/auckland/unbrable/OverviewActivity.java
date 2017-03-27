package nz.ac.auckland.unbrable;

import android.content.Intent;
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

        Entry entry1 = new Entry(new Date());
        entry1.setText("Item1");
        Entry entry2 = new Entry(new Date());
        entry2.setText("Item2");
        Entry entry3 = new Entry(new Date());
        entry3.setText("Item3");

        List<Entry> entries = new ArrayList<Entry>();
        // Load here
        entries.add(entry1);
        entries.add(entry2);
        entries.add(entry3);

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
                    bundle.putString("imageUri", entry.getImageBitmap().toString());
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
}
