package com.example.huliaaaa.groupcalendarproject;

import android.support.annotation.Nullable;

import java.util.ArrayList;

/**
 * Created by Huliaaaa on 10/17/2017.
 */

public class GroupEvent {
    private int color;
    private long timeInMillis;
    private Object data;
    private Object description;
    private ArrayList<String> friends;



    public GroupEvent(int _color, long _timeInMillis, Object _data, Object _description, ArrayList<String> _friends) {
        color = _color;
        timeInMillis = _timeInMillis;
        data = _data;
        description = _description;
        friends = _friends;
    }
    public GroupEvent(int _color, long _timeInMillis, Object _data,  ArrayList<String> _friends)
    {
        color = _color;
        timeInMillis = _timeInMillis;
        data = _data;
        friends = _friends;
    }

    public ArrayList<String> getFriends() { return friends; }
    public void setFriends(ArrayList<String> friends) { this.friends = friends; }

    public int getColor() {
        return color;
    }

    public long getTimeInMillis() {
        return timeInMillis;
    }

    @Nullable
    public Object getData() {
        return data;
    }

    @Nullable
    public Object getDescription(){ return description; }

    public void setColor(int color) {
        this.color = color;
    }

    public void setTimeInMillis(long timeInMillis) {
        this.timeInMillis = timeInMillis;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public void setDescription(Object description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Event{" +
                "color=" + color +
                ", timeInMillis=" + timeInMillis +
                ", data=" + data +
                '}';
    }
}

