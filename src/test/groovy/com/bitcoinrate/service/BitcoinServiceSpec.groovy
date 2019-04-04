package com.bitcoinrate.service


import com.google.api.client.http.HttpRequestFactory
import com.google.api.client.http.HttpTransport

class BitcoinServiceSpec extends BaseSpecification {
    def "Current Bitcoin rate"(){
        given: "a httpRequestFactory instance"
        HttpTransport transport = mockHttpTransport
        HttpRequestFactory httpRequestFactory = transport.createRequestFactory()

        and: "a BitcoinService instance that uses httpRequestFactory as injection"
        BitcoinService bitcoinService = new BitcoinService(httpRequestFactory)

        when: "the getCurrentPrice method is invoked"
        String currentPrice = bitcoinService.getCurrentPrice("USD")

        then: "the service should interact with the factory to parse de request to data entity"
        currentPrice == "5,008.2033"
    }
}
