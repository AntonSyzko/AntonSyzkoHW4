package com.example.hw4;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

public class PhotoActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;//this is code  of hte very activity - in second activity intent calls this code to get result
    private ImageView image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_capture);

        image = (ImageView) findViewById(R.id.image);
        capturePhoto();
    }

    public void capturePhoto() {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);//setted in second  activity
        if (intent.resolveActivity(getPackageManager()) != null) {//smth is  happening in case of
            startActivityForResult(intent, REQUEST_IMAGE_CAPTURE);//to send to Second Activity -onResultSet is calling extras by this ID
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bitmap readyImage = data.getParcelableExtra("data");//we will send to Second activity in extra - data
            image.setImageBitmap(readyImage);
        }
    }
}
