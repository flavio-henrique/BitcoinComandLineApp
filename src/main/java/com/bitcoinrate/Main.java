package com.bitcoinrate;

import com.bitcoinrate.service.BitcoinService;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.javanet.NetHttpTransport;

public class Main {
    public static void main(String[] args) {
        HttpRequestFactory httpRequestFactory = new NetHttpTransport()
                .createRequestFactory();
        BitcoinService bitcoinService = new BitcoinService(httpRequestFactory);

        Application app = new Application(bitcoinService);
        app.run();
    }
}
