package com.example.hw4;

import android.graphics.Bitmap;

/**
 * Created by p_val on 17.09.15.
 */
public class ListItemObject {
    private String place;
    private String time;
    private String date;
    private Bitmap image;

    public ListItemObject() {
    }

    public ListItemObject(String place, String time, String date, Bitmap image) {
        this.place = place;
        this.time = time;
        this.date = date;
        this.image = image;
    }

    @Override
    public String toString() {
        return "ListItemObject{" +
                "place='" + place + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ListItemObject that = (ListItemObject) o;

        if (place != null ? !place.equals(that.place) : that.place != null) return false;
        if (time != null ? !time.equals(that.time) : that.time != null) return false;
        return !(date != null ? !date.equals(that.date) : that.date != null);

    }

    @Override
    public int hashCode() {
        int result = place != null ? place.hashCode() : 0;
        result = 31 * result + (time != null ? time.hashCode() : 0);
        result = 31 * result + (date != null ? date.hashCode() : 0);
        return result;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getTime() {
        return time == null ? "" : time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date == null ? "" : date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }
}
