package com.example.usuario.quedapp.beans;

import java.util.ArrayList;

/**
 * Created by Warshock-Programador on 12/02/2015.
 */

public class Event {
    private int idEvento;
    private String nameEvent;
    private String nameLocation;
    private int mYear;
    private int mMonth;
    private int mDay;
    private int mHour;
    private String description;
    private ArrayList contacts;

    public Event() {
    }

    public Event(int idEvento, String nameEvent, String nameLocation, int mYear, int mMonth, int mDay, int mHour, String description, ArrayList contacts) {
        this.idEvento = idEvento;
        this.nameEvent = nameEvent;
        this.nameLocation = nameLocation;
        this.mYear = mYear;
        this.mMonth = mMonth;
        this.mDay = mDay;
        this.mHour = mHour;
        this.description = description;
        this.contacts = contacts;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public String getNameLocation() {
        return nameLocation;
    }

    public void setNameLocation(String nameLocation) {
        this.nameLocation = nameLocation;
    }

    public int getmYear() {
        return mYear;
    }

    public void setmYear(int mYear) {
        this.mYear = mYear;
    }

    public int getmMonth() {
        return mMonth;
    }

    public void setmMonth(int mMonth) {
        this.mMonth = mMonth;
    }

    public int getmDay() {
        return mDay;
    }

    public void setmDay(int mDay) {
        this.mDay = mDay;
    }

    public int getmHour() {
        return mHour;
    }

    public void setmHour(int mHour) {
        this.mHour = mHour;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList getContacts() {
        return contacts;
    }

    public void setContacts(ArrayList contacts) {
        this.contacts = contacts;
    }
}
