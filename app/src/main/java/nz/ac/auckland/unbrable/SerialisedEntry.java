package nz.ac.auckland.unbrable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;

import java.util.Date;

public class SerialisedEntry extends Entry {

    public SerialisedEntry(long date, String imageBitmap, String text){
        super(new Date((date)), stringToBitmap(imageBitmap), text);
    }

    // http://stackoverflow.com/questions/13562429/how-many-ways-to-convert-bitmap-to-string-and-vice-versa
    private static Bitmap stringToBitmap(String imageBitmap) {
        try {
            byte [] encodeByte = Base64.decode(imageBitmap,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }
}
