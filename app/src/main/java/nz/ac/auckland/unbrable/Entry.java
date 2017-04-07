package nz.ac.auckland.unbrable;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Entry {
    private Uri _imageUri = null;
    private Date _date = null;
    private String _text = null;

    public Entry(Date date) {
        this._date = date;
    }

    public Entry(Date date, Uri imageUri, String text) {
        this._date = date;
        this._imageUri = imageUri;
        this._text = text;
    }

    public Entry(Date date, String text) {
        this._date = date;
        this._text = text;
    }

    public Entry(Date date, Uri imageUri){
        this._date = date;
        this._imageUri = imageUri;
    }

    public void setImageUri(Bitmap image) { this._imageUri = _imageUri; }

    public void setDate(Date date) { this._date = date; }

    public void setText(String text) { this._text = text; }

    public Uri getImageUri() {
        return _imageUri;
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
            values.put(DiaryEntryContract.DiaryEntryColumns.COLUMN_NAME_IMAGE, _imageUri.toString());

            db.insert(DiaryEntryContract.DiaryEntryColumns.TABLE_NAME, null, values);
            dbHelper.close();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
