package com.bitcoinrate;

import com.bitcoinrate.model.HistoricalResponse;
import com.bitcoinrate.service.BitcoinService;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponseException;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonObjectParser;
import com.google.api.client.json.jackson2.JacksonFactory;

import java.util.Scanner;

public class Application {
    private Scanner scanner;

    Application(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        HttpRequestFactory httpRequestFactory = new NetHttpTransport()
                .createRequestFactory((HttpRequest request) -> request.setParser(new JsonObjectParser(new JacksonFactory())));

        BitcoinService bitcoinService = new BitcoinService(httpRequestFactory);


        System.out.println("Please inform the currency (For Example: USD, EUR, GBP, etc.): ");
        String currency = scanner.nextLine();

        try {
            String rate = bitcoinService.getCurrentPrice(currency);
            System.out.println("The current Bitcoin rate is: " + rate);
        } catch (HttpResponseException e) {
            System.out.println("Error to get the current Bitcoin rate: " + e.getContent());
        } catch (Exception e) {
            System.out.println("It was not possible to retrieve the current Bitcoin rate.");
        }

        try {
            HistoricalResponse historicalResponse = bitcoinService.getHistoricalPrice(currency);
            bitcoinService.getLowestValue(historicalResponse.getBpi().values())
                    .ifPresent((min) -> System.out.println("The lowest Bitcoin rate in the last 30 days is: " + min));

            bitcoinService.getHighestValue(historicalResponse.getBpi().values())
                    .ifPresent((max) -> System.out.println("The highest Bitcoin rate in the last 30 days is: " + max));
        } catch (HttpResponseException e) {
            System.out.println("Error to retrieve rate in the last 30 days: " + e.getContent());
        } catch (Exception e) {
            System.out.println("It was not possible to retrieve rate in the last 30 days");
        }
    }
}
