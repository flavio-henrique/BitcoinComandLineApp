package com.bitcoinrate.model;

public class Time {
    public Time(String updated) {
        this.updated = updated;
    }
    private String updated;

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
