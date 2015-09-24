package com.example.hw4;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class SecondActivity extends ActionBarActivity {
    private static final int REQUEST_IMAGE_CAPTURE = 1;//code to go to photo activity
    private static final String TAG = "SecondActivity";

    private ImageView secondActivityImage;
    private Button setDateButton;
    private Button setTimeButton;
    private static TextView timeText;
    private static TextView dateText;
    private EditText placeEditedText;
    private Bitmap iconSecondAct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);

        secondActivityImage = (ImageView) findViewById(R.id.second_activity_image);
        setDateButton = (Button) findViewById(R.id.set_date_but);
        setTimeButton = (Button) findViewById(R.id.set_time_but);
        timeText = (TextView) findViewById(R.id.time_text_v_sec_act);
        dateText = (TextView) findViewById(R.id.date_text_view_sec_act);
        placeEditedText = (EditText) findViewById(R.id.place_edit_text_sec_act);

        secondActivityImage.setOnClickListener(onImageClick());
        setDateButton.setOnClickListener(onSetDateClick());
        setTimeButton.setOnClickListener(onSetTimeClick());

    }






    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_adds_new, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {//V in menu
        switch (item.getItemId()) {
            case R.id.action_apply:
                Intent intent = new Intent();//if V clicked - new intent - back to main activity

                if (iconSecondAct != null) {
                    intent.putExtra("image", iconSecondAct);
                }

                if (dateText.getText().toString().length() > 0) {
                    intent.putExtra("dateText", dateText.getText().toString());
                }

                if (timeText.getText().toString().length() > 0) {
                    intent.putExtra("timeText", timeText.getText().toString());
                }

                if (placeEditedText.getText().toString().length() > 0) {
                    intent.putExtra("placeEditedText", placeEditedText.getText().toString());
                }

                setResult(RESULT_OK, intent);//set finally all rsult and OK
                finish();//finishing - nothing to d0
                break;//break the  switch - case
        }
        return true;
    }

    private View.OnClickListener onSetTimeClick() {//set Time  button
        return new View.OnClickListener() {//returns view
            @Override
            public void onClick(View v) {
                DialogFragment TimePickFragment = new TimePickerFragment();
                TimePickFragment.show(getFragmentManager(), "timePicker");
            }
        };
    }

    private View.OnClickListener onSetDateClick() {//setDate button
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment DatePickFragment = new DatePickerFragment();
                DatePickFragment.show(getFragmentManager(), "datePicker");
            }
        };
    }

    private View.OnClickListener onImageClick() {//photo sapce click
        return new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        };
    }

    public void takePicture() {
        Intent goToCameraIntent = new Intent();
        goToCameraIntent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//new actictivity then photo Activity
        if (goToCameraIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(goToCameraIntent, REQUEST_IMAGE_CAPTURE);//to photo activity intent - thei is  code  of Photo activity
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {//getting from Photo Activity
            iconSecondAct = data.getParcelableExtra("data");//pulling extras from photo activity extra data
            secondActivityImage.setImageBitmap(iconSecondAct);
        }
    }

    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current timeText as the default values for the picker
            final Calendar c = Calendar.getInstance();
            int hour = c.get(Calendar.HOUR_OF_DAY);
            int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            timeText.setText(hourOfDay + ":" + minute);
        }
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current dateText as the default dateText in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            dateText.setText(year + "/" + month + "/" + day);
        }
    }
}
