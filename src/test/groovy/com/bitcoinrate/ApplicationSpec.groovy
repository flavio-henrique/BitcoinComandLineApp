package com.bitcoinrate

import com.bitcoinrate.service.BitcoinService
import com.google.api.client.http.HttpHeaders
import com.google.api.client.http.HttpResponse
import com.google.api.client.http.HttpResponseException

class ApplicationSpec extends BaseSpecification {
    def "Run the Application and interact with the BitcoinService"(){
        given: "a BitcoinService instance"
        BitcoinService bitcoinServiceMock = Mock(BitcoinService)

        and: "a valid currency for the input"
        String input = "USD"
        InputStream inputStream = new ByteArrayInputStream(input.getBytes())
        System.setIn(inputStream)

        and: "a Application instance that uses BitcoinService as injection"
        Application application = new Application(bitcoinServiceMock)

        when: "the run method is invoked"
        application.run()

        then: "should get the current Bitcoin rate"
        1 * bitcoinServiceMock.getCurrentPrice("USD")

        and: "should get the historical Bitcoin rate"
        1 * bitcoinServiceMock.getHistoricalPrice("USD") >> historicalResponse

        and: "should get lowest and highest Bitcoin rate in a certain period"
        1 * bitcoinServiceMock.getLowestValue({ [4977.0017D, 3880.8D, 3884.9983D] } ) >> OptionalDouble.of(3880.8D)
        1 * bitcoinServiceMock.getHighestValue({ [4977.0017D, 3880.8D, 3884.9983D] } ) >> OptionalDouble.of(4977.0017D)
    }

    def "Handle HttpResponseException"(){
        given: "a BitcoinService instance"
        BitcoinService bitcoinServiceMock = Mock(BitcoinService)

        and: "a incorrect currency for the input"
        String input = "InvalidCurrency"
        InputStream inputStream = new ByteArrayInputStream(input.getBytes())
        System.setIn(inputStream)

        and: "a Application instance that uses BitcoinService as injection"
        Application application = new Application(bitcoinServiceMock)

        when: "the run method is invoked"
        application.run()

        then: "should get the historical Bitcoin rate"
        1 * bitcoinServiceMock.getCurrentPrice("InvalidCurrency") >>
                { throw new HttpResponseException.Builder(404, "Currency not found", new HttpHeaders()).build() }

        and: "should get the historical Bitcoin rate"
        1 * bitcoinServiceMock.getHistoricalPrice("InvalidCurrency") >>
                { throw new HttpResponseException.Builder(404, "Currency not found", new HttpHeaders()).build() }

        and: "should not get lowest and highest Bitcoin rate in a certain period"
        0 * bitcoinServiceMock.getLowestValue({ [4977.0017D, 3880.8D, 3884.9983D] } ) >> OptionalDouble.of(3880.8D)
        0 * bitcoinServiceMock.getHighestValue({ [4977.0017D, 3880.8D, 3884.9983D] } ) >> OptionalDouble.of(4977.0017D)
    }

    def "Handle IOException"(){
        given: "a BitcoinService instance"
        BitcoinService bitcoinServiceMock = Mock(BitcoinService)

        and: "a incorrect currency for the input"
        String input = "InvalidCurrency"
        InputStream inputStream = new ByteArrayInputStream(input.getBytes())
        System.setIn(inputStream)

        and: "a Application instance that uses BitcoinService as injection"
        Application application = new Application(bitcoinServiceMock)

        when: "the run method is invoked"
        application.run()

        then: "should get the historical Bitcoin rate"
        1 * bitcoinServiceMock.getCurrentPrice("InvalidCurrency") >> { throw new IOException() }

        and: "should get the historical Bitcoin rate"
        1 * bitcoinServiceMock.getHistoricalPrice("InvalidCurrency") >> { throw new IOException() }

        and: "should not get lowest and highest Bitcoin rate in a certain period"
        0 * bitcoinServiceMock.getLowestValue({ [4977.0017D, 3880.8D, 3884.9983D] } ) >> OptionalDouble.of(3880.8D)
        0 * bitcoinServiceMock.getHighestValue({ [4977.0017D, 3880.8D, 3884.9983D] } ) >> OptionalDouble.of(4977.0017D)
    }
}
