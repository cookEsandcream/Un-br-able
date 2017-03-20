package nz.ac.auckland.unbrable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;


public class DiaryEntryAdapter extends ArrayAdapter<DiaryEntry> {

    private static class ViewHolder {
        private ImageView thumbnail;
        private TextView text;
    }

    public DiaryEntryAdapter(Context context, int resourceId, List<DiaryEntry> items) {
        super(context, resourceId, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            LayoutInflater vi = LayoutInflater.from(getContext());
            view = vi.inflate(R.layout.list_item, null);

            viewHolder = new ViewHolder();

            viewHolder.thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
            viewHolder.text = (TextView) view.findViewById(R.id.textView);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        DiaryEntry entry = getItem(position);

        if(entry != null) {

            if(entry.getImage() != null) {
                viewHolder.thumbnail.setImageURI(entry.getImage());
            }

            if(entry.getText() != null) {
                viewHolder.text.setText(entry.getText());
            }
        }

        return view;
    }
}
