package nz.ac.auckland.unbrable;


import android.content.Context;
import android.graphics.Bitmap;
import android.net.Uri;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Entry {
    private Bitmap _imageBitmap = null;
    private Date _date = null;
    private String _text = null;

    public Entry(Date date) {
        this._date = date;
    }

    public Entry(Date date, Bitmap imageBitmap, String text) {
        this._date = date;
        this._imageBitmap = imageBitmap;
        this._text = text;
    }

    public Entry(Date date, String text) {
        this._date = date;
        this._text = text;
    }

    public Entry(Date date, Bitmap imageBitmap) {
        this._date = date;
        this._imageBitmap = imageBitmap;
    }

    public void setImageBitmap(Bitmap image) { this._imageBitmap = image; }

    public void setDate(Date date) { this._date = date; }

    public void setText(String text) { this._text = text; }

    public Bitmap getImageBitmap() {
        return _imageBitmap;
    }

    public Date getDate() {
        return _date;
    }

    public String getStringDate() {
        DateFormat dateFormat = new SimpleDateFormat("EEEE dd MMMM yyyy");
        return dateFormat.format(_date);
    }

    public String getText() {
        return _text;
    }

    public void save(Context context){

        DiaryEntryDbHelper dbHelper = new DiaryEntryDbHelper(context);
    }
}
