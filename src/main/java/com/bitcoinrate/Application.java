package com.bitcoinrate;

import com.bitcoinrate.model.HistoricalResponse;
import com.bitcoinrate.service.BitcoinService;
import com.google.api.client.http.HttpResponseException;

import java.io.IOException;
import java.util.Scanner;

public class Application {
    private BitcoinService bitcoinService;

    public Application(BitcoinService bitcoinService) {
        this.bitcoinService = bitcoinService;
    }

    public void run() {

        System.out.println("Please inform the currency (For Example: USD, EUR, GBP, etc.): ");
        Scanner scanner = new Scanner(System.in);
        String currency = scanner.nextLine();

        try {
            String rate = bitcoinService.getCurrentPrice(currency);
            System.out.println("The current Bitcoin rate is: " + rate);
        } catch (HttpResponseException e) {
            System.out.println("Error to get the current Bitcoin rate: " + e.getContent());
        } catch (IOException e) {
            System.out.println("It was not possible to retrieve the current Bitcoin rate.");
        }

        try {
            HistoricalResponse historicalResponse = bitcoinService.getHistoricalPrice(currency);
            bitcoinService.getLowestValue(historicalResponse.getBpi().values())
                    .ifPresent((min) -> System.out.printf("The lowest Bitcoin rate in the last 30 days is: %,.4f\n", min));

            bitcoinService.getHighestValue(historicalResponse.getBpi().values())
                    .ifPresent((max) -> System.out.printf("The highest Bitcoin rate in the last 30 days is: %,.4f\n", max));
        } catch (HttpResponseException e) {
            System.out.println("Error to retrieve rate in the last 30 days: " + e.getContent());
        } catch (IOException e) {
            System.out.println("It was not possible to retrieve rate in the last 30 days");
        }
    }
}
