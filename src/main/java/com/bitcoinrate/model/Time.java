package com.bitcoinrate.model;

import com.google.api.client.util.DateTime;
import com.google.api.client.util.Key;

public class Time {
    @Key
    private String updated;

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }
}
