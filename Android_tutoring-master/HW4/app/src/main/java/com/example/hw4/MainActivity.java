package com.example.hw4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_CODE_NEW_ADD = 1;//code for result
    private static final String TAG = "AddsListActivity";

    private ListView list;//my listV of  items
    private ArrayList<ListItemObject> items = new ArrayList<>();//the very list
    private MyItemListAdapter adapter;//adapter for intent

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_list);

        adapter = new MyItemListAdapter(this, items);//this activity - we will put item from the list

        list = (ListView) findViewById(R.id.main_activity_list);
        list.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adds_list, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//the + button menu
        switch (item.getItemId()) {
            case R.id.action_add://case it is clicked
                Intent goToSecondActivityIntent = new Intent(MainActivity.this, SecondActivity.class);
                startActivityForResult(goToSecondActivityIntent, REQUEST_CODE_NEW_ADD);
                break;
        }
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {//getting data from second activity - extras
        if (resultCode != RESULT_CANCELED)//if not cancellaed
            if (requestCode == REQUEST_CODE_NEW_ADD && resultCode == RESULT_OK) {//if it is the result by our  code - not  other
                String place = data.getStringExtra("placeEditedText");//getting extras from second activity
                String date = data.getStringExtra("dateText");
                String time = data.getStringExtra("timeText");
                Bitmap bmp = null;
                try {
                    bmp = data.getParcelableExtra("image");
                } catch (NullPointerException e) {
                    Log.e(TAG, "byte array is null", e);
                }

                items.add(new ListItemObject(place, time, date, bmp));//adding to my aaray list the  object - we pull our data into it
                adapter.notifyDataSetChanged();//tell the intent i am ready
            }
    }
}
