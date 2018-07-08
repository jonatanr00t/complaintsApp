package com.jonatan.criminalintent.entities;

import java.util.Date;
import java.util.UUID;

/**
 * Created by jona on 08/11/17.
 */

public class Crime {

    private UUID mId;
    private String mTitle;
    private Date mFecha;
    private boolean mSolved;

    public Crime() {
        //Generate unique identifier
        mId = UUID.randomUUID();
        mFecha = new Date();
    }

    public UUID getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public Date getFecha() {
        return mFecha;
    }

    public void setFecha(Date fecha) {
        mFecha = fecha;
    }

    public boolean isSolved() {
        return mSolved;
    }

    public void setSolved(boolean solved) {
        mSolved = solved;
    }
}
