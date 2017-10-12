package com.example.huliaaaa.groupcalendarproject;

import android.support.annotation.Nullable;

import com.github.sundeepk.compactcalendarview.domain.Event;

/**
 * Created by Huliaaaa on 10/10/2017.
 */

public class OurEvent {
    private int color;
    private long timeInMillis;
    private Object data;
    private Object description;

    public OurEvent(int _color, long _timeInMillis, Object _data, Object _description) {
        color = _color;
        timeInMillis = _timeInMillis;
        data = _data;
        description = _description;
    }

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
