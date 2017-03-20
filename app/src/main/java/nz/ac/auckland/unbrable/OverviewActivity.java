package nz.ac.auckland.unbrable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class OverviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Diary Entries");
        setContentView(R.layout.activity_overview);
        
        populateEntries();
    }

    private void populateEntries() {

        DiaryEntry entry1 = new DiaryEntry();
        entry1.setText("Item1");
        DiaryEntry entry2 = new DiaryEntry();
        entry2.setText("Item2");
        DiaryEntry entry3 = new DiaryEntry();
        entry3.setText("Item3");

        List<DiaryEntry> entries = new ArrayList<DiaryEntry>();
        entries.add(entry1);
        entries.add(entry2);
        entries.add(entry3);

        DiaryEntryAdapter adapter = new DiaryEntryAdapter(this, R.layout.list_item, entries);

        ListView list = (ListView) findViewById(R.id.listView);
        list.setAdapter(adapter);
    }
}
