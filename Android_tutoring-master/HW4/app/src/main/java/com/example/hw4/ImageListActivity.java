package com.example.hw4;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

public class ImageListActivity extends Activity {
    private ListView list;
    private String[] items;
    private ImageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_list);

        items = new String[]{"1", "2", "3"};

        adapter = new ImageAdapter(this, items);

        list = (ListView) findViewById(R.id.main_activity_list);
        list.setAdapter(adapter);
    }
}
