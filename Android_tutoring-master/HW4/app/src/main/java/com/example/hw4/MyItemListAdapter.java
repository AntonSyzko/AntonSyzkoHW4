package com.example.hw4;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyItemListAdapter extends ArrayAdapter<ListItemObject> {
    private final Context context;
    private final ArrayList<ListItemObject> values;

    public MyItemListAdapter(Context context, ArrayList<ListItemObject> values) {
        super(context, R.layout.item_list, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.my_items_list, parent, false);

        TextView place = (TextView) rowView.findViewById(R.id.main_place_text);
        TextView dateTime = (TextView) rowView.findViewById(R.id.date_time_main);
        ImageView icon = (ImageView) rowView.findViewById(R.id.main_list_image);

        String placeStr = values.get(position).getPlace();
        String dateStr = values.get(position).getDate();
        String timeStr = values.get(position).getTime();
        Bitmap image = values.get(position).getImage();

        place.setText(placeStr);
        dateTime.setText(dateStr + " " + timeStr);
        icon.setImageBitmap(image);
        return rowView;
    }
}
