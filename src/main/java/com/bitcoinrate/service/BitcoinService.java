package com.bitcoinrate.service;

import com.bitcoinrate.model.CoinDeskUrl;
import com.bitcoinrate.model.CurrentPriceResponse;
import com.bitcoinrate.model.HistoricalResponse;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.gson.Gson;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class BitcoinService {
    private HttpRequestFactory httpRequestFactory;

    public BitcoinService(HttpRequestFactory httpRequestFactory) {
        this.httpRequestFactory = httpRequestFactory;
    }

    public String getCurrentPrice(String currency) throws IOException {
        CoinDeskUrl currentPriceUrl = new CoinDeskUrl("https://api.coindesk.com/v1/bpi/currentprice/" + currency + ".json");
        HttpRequest request = httpRequestFactory.buildGetRequest(currentPriceUrl);
        HttpResponse httpResponse = request.execute();
        Gson gson = new Gson();
        CurrentPriceResponse currentPriceResponse = gson.fromJson(IOUtils.toString(httpResponse.getContent(), StandardCharsets.UTF_8.name()), CurrentPriceResponse.class);
        return currentPriceResponse.getBpi()
                .get(currency)
                .getRate();
    }

    private String getStartDate() {
        Date currentDate = new Date();
        Calendar cal = new GregorianCalendar();
        cal.setTime(currentDate);
        cal.add(Calendar.DAY_OF_MONTH, -30);
        Date startDate = cal.getTime();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormat.format(startDate);
    }

    private String getEndDate() {
        Date endDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat( "yyyy-MM-dd");
        return dateFormat.format(endDate);
    }

    public HistoricalResponse getHistoricalPrice(String currency) throws Exception{
        CoinDeskUrl historicalPriceUrl = new CoinDeskUrl("https://api.coindesk.com/v1/bpi/historical/close.json");
        historicalPriceUrl.currency = currency;
        historicalPriceUrl.start = getStartDate();
        historicalPriceUrl.end = getEndDate();

        HttpRequest request = httpRequestFactory.buildGetRequest(historicalPriceUrl);

        HttpResponse httpResponse = request.execute();
        Gson gson = new Gson();
        return gson.fromJson(IOUtils.toString(httpResponse.getContent(), StandardCharsets.UTF_8.name()), HistoricalResponse.class);
    }

    public OptionalDouble getLowestValue(Collection<Float> values) {
        return values.stream()
                .mapToDouble((value) -> value)
                .min();
    }

    public OptionalDouble getHighestValue(Collection<Float> values) {
        return values.stream()
                .mapToDouble((value) -> value)
                .max();
    }
}
