package com.bitcoinrate.model;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.util.Key;

public class CoinDeskUrl extends GenericUrl{
    public CoinDeskUrl(String encodedUrl) {
        super(encodedUrl);
    }

    @Key
    public String currency;
    @Key
    public String start;
    @Key
    public String end;
}
