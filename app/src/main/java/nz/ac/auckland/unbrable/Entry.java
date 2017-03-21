package nz.ac.auckland.unbrable;


import android.net.Uri;

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

    public Entry(Date date, Uri imageUri) {
        this._date = date;
        this._imageUri = imageUri;
    }

    public void setImageUri(Uri image) { this._imageUri = image; }

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
}
