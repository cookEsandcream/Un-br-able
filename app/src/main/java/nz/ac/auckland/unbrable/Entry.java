package nz.ac.auckland.unbrable;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
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
        try {
            DiaryEntryDbHelper dbHelper = new DiaryEntryDbHelper(context);
            SQLiteDatabase db = dbHelper.getWritableDatabase();

            ContentValues values = new ContentValues();
            values.put(DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_DATE, getDate().getTime());
            values.put(DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_ENTRY, getText());
            values.put(DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_BITMAP, bitmapToString(getImageBitmap()));

            db.insert(DiaryEntryContract.DiaryEntryColumns.TABLE_NAME, null, values);
            dbHelper.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // http://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versa
    public String bitmapToString(Bitmap bitmap){
        if (bitmap == null){
            return null;
        }
        ByteArrayOutputStream baos = new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b = baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}
