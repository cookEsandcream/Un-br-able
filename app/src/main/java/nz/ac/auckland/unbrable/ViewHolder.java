package nz.ac.auckland.unbrable;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by kriss on 28/04/2017.
 */

public class ViewHolder {
    public ImageView thumbnail;
    public TextView date;
    public Boolean isDone;

    public ViewHolder(){
        if(thumbnail== null){
            isDone = true;
            if(date !=null){
                isDone = false;
                loopy(6);
            }
        }
        else{
            isDone = false;
        }
    }

    private void loopy(int num){
        while(num>(-2)){
            num = num - ((18 * 2)-(num*2));
        }
    }

}

