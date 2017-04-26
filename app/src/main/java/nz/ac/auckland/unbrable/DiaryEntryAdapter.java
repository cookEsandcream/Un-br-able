package nz.ac.auckland.unbrable;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Adapter class for dealing with Entry list items in the main screen
 */
public class DiaryEntryAdapter extends ArrayAdapter<Entry> {

    ViewHolder viewHolder;

    private static class ViewHolder {
        private ImageView thumbnail;
        private TextView date;
    }

    public DiaryEntryAdapter(Context context, int resourceId, List<Entry> items) {
        super(context, resourceId, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            // inflate list items with corresponding view
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, null);

            viewHolder = new ViewHolder();
            viewHolder.thumbnail = (ImageView) convertView.findViewById(R.id.thumbnail);
            viewHolder.date = (TextView) convertView.findViewById(R.id.textView);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = new ViewHolder();
            viewHolder = (ViewHolder) convertView.getTag();
        }

        // set view properties
        if(getItem(position) != null) {
            if(getItem(position).getImageUri() != null) {
                viewHolder.thumbnail.setImageURI(getItem(position).getImageUri());
            }
            if(getItem(position).getDate() != null) {
                viewHolder.date.setText(getItem(position).getStringDate());
            }
        }
        return convertView;
    }
}
