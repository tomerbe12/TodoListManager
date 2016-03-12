package il.ac.huji.todolist;


import android.content.Context;
import android.graphics.Color;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.ArrayList;


/**
 * Created by tomerbe on 12/03/2016.
 */
public class MyArrayAdapter extends ArrayAdapter<String> {

    public MyArrayAdapter(Context context, int itemsres) {
        super(context, itemsres);
    }
    public MyArrayAdapter(Context context, @LayoutRes int itemsres, @NonNull ArrayList<String> items) {
        super(context, itemsres, items);
        }

    public View getView(int position, View convertView, ViewGroup parent){
        TextView  view = (TextView)convertView;
        if (view == null) {
            view = (TextView)super.getView(position,convertView, parent);
             }
         else {
            String item = getItem(position);
            view.setText(item);
               }
        view.setTextColor(position % 2 == 0 ? Color.RED : Color.BLUE);
        return view;
    }
}
