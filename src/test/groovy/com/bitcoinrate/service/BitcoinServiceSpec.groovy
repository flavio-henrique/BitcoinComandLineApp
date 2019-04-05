package com.bitcoinrate.service

import com.bitcoinrate.BaseSpecification
import com.bitcoinrate.model.HistoricalResponse
import com.google.api.client.http.HttpRequestFactory
import com.google.api.client.http.HttpTransport

class BitcoinServiceSpec extends BaseSpecification {
    def "Retrive Current Bitcoin rate"(){
        given: "a httpRequestFactory instance"
        HttpTransport transport = mockHttpTransport
        HttpRequestFactory httpRequestFactory = transport.createRequestFactory()

        and: "a BitcoinService instance that uses httpRequestFactory as injection"
        BitcoinService bitcoinService = new BitcoinService(httpRequestFactory)

        when: "the getCurrentPrice method is invoked"
        String currentPrice = bitcoinService.getCurrentPrice("USD")

        then: "a current price should be returned"
        currentPrice == "5,008.2033"
    }

    def "Retrieve historical Bitcoin rate"(){
        given: "a httpRequestFactory instance"
        HttpTransport transport = mockHistoricalRate
        HttpRequestFactory httpRequestFactory = transport.createRequestFactory()

        and: "a BitcoinService instance that uses httpRequestFactory as injection"
        BitcoinService bitcoinService = new BitcoinService(httpRequestFactory)

        when: "the getHistoricalPrice method is invoked"
        HistoricalResponse historicalResponse = bitcoinService.getHistoricalPrice("USD")

        then: "A HistoricalResponse should be returned"
        historicalResponse.getBpi().values().size() == 1
    }

    def "Get lowest Bitcoin rate"(){
        given: "a httpRequestFactory instance"
        HttpTransport transport = mockHistoricalRate
        HttpRequestFactory httpRequestFactory = transport.createRequestFactory()

        and: "a BitcoinService instance that uses httpRequestFactory as injection"
        BitcoinService bitcoinService = new BitcoinService(httpRequestFactory)

        when: "the getHistoricalPrice method is invoked"
        OptionalDouble value = bitcoinService.getLowestValue(Arrays.asList(3880.8D, 4977.0017D))

        then: "the lowest rate should be returned"
        value.getAsDouble() == 3880.8D
    }

    def "Get highest Bitcoin rate"(){
        given: "a httpRequestFactory instance"
        HttpTransport transport = mockHistoricalRate
        HttpRequestFactory httpRequestFactory = transport.createRequestFactory()

        and: "a BitcoinService instance that uses httpRequestFactory as injection"
        BitcoinService bitcoinService = new BitcoinService(httpRequestFactory)

        when: "the getHistoricalPrice method is invoked"
        OptionalDouble value = bitcoinService.getHighestValue(Arrays.asList(3880.8D, 4977.0017D))

        then: "the lowest rate should be returned"
        value.getAsDouble() == 4977.0017D
    }

}
