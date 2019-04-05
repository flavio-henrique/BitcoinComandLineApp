package com.bitcoinrate.model;

import java.util.HashMap;
import java.util.Map;

public class HistoricalResponse {
    private String disclaimer;
    private Time time;
    private Map<String, Double> bpi = new HashMap<>();

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

    public Map<String, Double> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, Double> bpi) {
        this.bpi = bpi;
    }

    public static class Builder {
        HistoricalResponse historicalResponse = new HistoricalResponse();

        public Builder withDisclaimer(String disclaimer) {
            historicalResponse.setDisclaimer(disclaimer);
            return this;
        }

        public Builder withTime(Time time) {
            historicalResponse.setTime(time);
            return this;
        }

        public Builder withBpi(Map<String, Double> bpi) {
            historicalResponse.setBpi(bpi);
            return this;
        }

        public HistoricalResponse build() {
            return historicalResponse;
        }
    }
}
