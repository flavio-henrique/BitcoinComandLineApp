package com.bitcoinrate.model;

import com.google.api.client.util.Key;

import java.util.HashMap;
import java.util.Map;

public class CurrentPriceResponse {
    @Key
    private Time time;
    @Key
    private String disclaimer;
    @Key
    private Map<String, Bpi> bpi = new HashMap<>();

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }


    public Map<String, Bpi> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, Bpi> bpi) {
        this.bpi = bpi;
    }
}
