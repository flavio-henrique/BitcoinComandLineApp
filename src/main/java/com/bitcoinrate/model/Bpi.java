package com.bitcoinrate.model;

import com.google.api.client.util.Key;

public class Bpi {
    @Key
    private String code;
    @Key
    private String rate;
    @Key
    private String description;
    @Key
    private float rate_float;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRate_float() {
        return rate_float;
    }

    public void setRate_float(float rate_float) {
        this.rate_float = rate_float;
    }
}
