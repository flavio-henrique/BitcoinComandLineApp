package com.bitcoinrate.model;

import java.util.HashMap;
import java.util.Map;

public class CurrentPriceResponse {

    private Time time;

    private String disclaimer;

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
