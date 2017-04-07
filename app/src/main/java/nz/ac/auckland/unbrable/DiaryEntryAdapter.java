package nz.ac.auckland.unbrable;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class DiaryEntryAdapter extends ArrayAdapter<Entry> {

    private static class ViewHolder {
        private ImageView thumbnail;
        private TextView date;
    }

    public DiaryEntryAdapter(Context context, int resourceId, List<Entry> items) {
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
            viewHolder.date = (TextView) view.findViewById(R.id.textView);

            view.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) view.getTag();
        }

        Entry entry = getItem(position);

        if(entry != null) {

            if(entry.getImageUri() != null) {
                viewHolder.thumbnail.setImageURI(entry.getImageUri());
            }

            if(entry.getDate() != null) {
                viewHolder.date.setText(entry.getStringDate());
            }
        }

        return view;
    }
}
