package com.bitcoinrate

import com.bitcoinrate.model.HistoricalResponse
import com.bitcoinrate.model.Time
import com.google.api.client.http.LowLevelHttpRequest
import com.google.api.client.http.LowLevelHttpResponse
import com.google.api.client.json.Json
import com.google.api.client.testing.http.MockHttpTransport
import com.google.api.client.testing.http.MockLowLevelHttpRequest
import com.google.api.client.testing.http.MockLowLevelHttpResponse
import spock.lang.Specification

class BaseSpecification extends Specification {

    def getMockHttpTransport() {
        return new MockHttpTransport() {
            @Override
            LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
                return new MockLowLevelHttpRequest() {
                    @Override
                    LowLevelHttpResponse execute() throws IOException {
                        MockLowLevelHttpResponse response = new MockLowLevelHttpResponse()
                        response.addHeader("custom_header", "value")
                        response.setStatusCode(200)
                        response.setContentType(Json.MEDIA_TYPE)
                        response.setContent("{\"time\": { \"updated\": \"Apr 4, 2019 04:52:00 UTC\"  }," +
                                "    \"disclaimer\": \"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\"," +
                                "    \"chartName\": \"Bitcoin\"," +
                                "    \"bpi\": {" +
                                "        \"USD\": {" +
                                "            \"code\": \"USD\"," +
                                "            \"symbol\": \"&#36;\"," +
                                "            \"rate\": \"5,008.2033\"," +
                                "            \"description\": \"United States Dollar\"," +
                                "            \"rate_float\": 5008.2033" +
                                "        }" +
                                "    }" +
                                "}")
                        return response
                    }
                }
            }
        }
    }
    def getMockHistoricalRate() {
        return new MockHttpTransport() {
            @Override
            LowLevelHttpRequest buildRequest(String method, String url) throws IOException {
                return new MockLowLevelHttpRequest() {
                    @Override
                    LowLevelHttpResponse execute() throws IOException {
                        MockLowLevelHttpResponse response = new MockLowLevelHttpResponse()
                        response.addHeader("custom_header", "value")
                        response.setStatusCode(200)
                        response.setContentType(Json.MEDIA_TYPE)
                        response.setContent("{\"time\": { \"updated\": \"Apr 4, 2019 04:52:00 UTC\"  }," +
                                "    \"disclaimer\": \"This data was produced from the CoinDesk Bitcoin Price Index (USD). Non-USD currency data converted using hourly conversion rate from openexchangerates.org\"," +
                                "    \"bpi\": {" +
                                "        \"2019-03-05\": 3880.8" +
                                "    }" +
                                "}")
                        return response
                    }
                }
            }
        }
    }

    def getHistoricalResponse() {
        Map<String, Double> map = new HashMap<>()
        map.put("2019-03-05", 3880.8D)
        map.put("2019-03-06", 3884.9983D)
        map.put("2019-04-03", 4977.0017D)
        return new HistoricalResponse.Builder()
                .withDisclaimer("disclaimer")
                .withTime(new Time("Apr 4, 2019 17:25:01 UTC"))
                .withBpi(map)
                .build()
    }

}
