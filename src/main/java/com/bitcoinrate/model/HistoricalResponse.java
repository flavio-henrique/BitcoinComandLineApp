package com.bitcoinrate.model;

import com.google.api.client.util.Key;

import java.util.HashMap;
import java.util.Map;

public class HistoricalResponse {
    @Key
    private String disclaimer;
    @Key
    private Time time;
    @Key
    private Map<String, Float> bpi = new HashMap<>();

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public Map<String, Float> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, Float> bpi) {
        this.bpi = bpi;
    }
}
