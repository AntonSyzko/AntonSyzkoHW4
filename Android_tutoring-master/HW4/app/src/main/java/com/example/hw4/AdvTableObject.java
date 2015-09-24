package com.example.hw4;

import android.graphics.Bitmap;

/**
 * Created by Ыг on 24.09.2015.
 */
/*
object for my SQLlite database Table
fields are
id
place
time
date
img
 */
public class AdvTableObject {
    //string   values for  my table and columns
    public static  final String TABLE_NAME = "Advertisements";

    public static  final String ID_COLUMN_KEY = "id";
    public static  final String PLACE_COLUMN_KEY= "place";
    public static  final String DATE_COLUMN_KEY = "date";
    public static  final String TIME_COLUMN_KEY = "time";
    public static  final String IMAGE_COLUMN_KEY = "image";

    //ultimate array of column keys
    public static final String[] COLUMNS_ARRAY= {ID_COLUMN_KEY,PLACE_COLUMN_KEY,DATE_COLUMN_KEY,TIME_COLUMN_KEY,IMAGE_COLUMN_KEY};

    //the very columns objects values - entities kind of - abive are just string keys indicators
    private int id;
    private String place;
    private String date;
    private String time;
    private Bitmap image;

    public AdvTableObject() {
    }

    public AdvTableObject(int id, String place, String date, String time, Bitmap image) {
        this.place = place;
        this.date = date;
        this.time = time;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {

        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return "AdvTableObject{" +
                "id=" + id +
                ", place='" + place + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", image=" + image +
                '}';
    }


//in case place will be  compared - to filter similar adds
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AdvTableObject that = (AdvTableObject) o;

        return place.equals(that.place);

    }

    @Override
    public int hashCode() {
        return place.hashCode();
    }
}
