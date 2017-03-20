package nz.ac.auckland.unbrable;

import android.net.Uri;


public class DiaryEntry {

    private Uri _image = null;
    private String _text = null;

    public DiaryEntry() {

    }

    public DiaryEntry(Uri image, String text) {
        this._image = image;
        this._text = text;
    }

    public void setImage(Uri image) { this._image = image; }

    public void setText(String text) { this._text = text; }

    public Uri getImage() {
        return _image;
    }

    public String getText() {
        return _text;
    }
}

