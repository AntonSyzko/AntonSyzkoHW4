package com.example.hw4;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

/**
 * Created by Ыг on 24.09.2015.
 */
public class ImageToBytesAndBackConverter {

    public static byte[] bitmapToByteArray(Bitmap imageToConvert) {
        ByteArrayOutputStream bos = new ByteArrayOutputStream();//opening a stream
        imageToConvert.compress(Bitmap.CompressFormat.PNG, 100, bos);
        return bos.toByteArray();
    }

    public static Bitmap byteArrayToBitmap(byte[] byteArrayToImage) {
        return BitmapFactory.decodeByteArray(byteArrayToImage, 0, byteArrayToImage.length);
    }


}
